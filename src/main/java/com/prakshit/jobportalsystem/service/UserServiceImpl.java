package com.prakshit.jobportalsystem.service;

import com.prakshit.jobportalsystem.dto.UserRequestDTO;
import com.prakshit.jobportalsystem.dto.UserResponseDTO;
import com.prakshit.jobportalsystem.entity.User;
import com.prakshit.jobportalsystem.exceptions.EmailAlreadyExistsException;
import com.prakshit.jobportalsystem.exceptions.ResourceNotFoundException;
import com.prakshit.jobportalsystem.mapper.UserEntityDTOMapper;
import com.prakshit.jobportalsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    UserRepository userRepository;

    //constructor injection
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) { //to create user and save in db

        //to check email already exists
        if(userRepository.existsByEmail(userRequestDTO.getEmail())){
            throw new EmailAlreadyExistsException("Email Already Registered! Please Provide different Email.");
        }

        User user =   UserEntityDTOMapper.convertUserRequestDTOToUser(userRequestDTO); //converting request ot user

        User savedUser = userRepository.save(user); //saving user to db

        return UserEntityDTOMapper.convertUserToUserResponseDTO(savedUser); //convert saved user to user response
    }

    @Override
    public UserResponseDTO getUserById(Long id) {

//        Optional<User> optionalUser = userRepository.findById(id);
//        if (optionalUser.isEmpty()) {
//            throw new ResourceNotFoundException("User not found with id " + id);
//        }
//        User user = optionalUser.get();

        User user =  userRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("User does Not Exists with Id "+id)); //it returns Optional ,If the Optional is empty throws exception

        return   UserEntityDTOMapper.convertUserToUserResponseDTO(user); //returning response

    }

    @Override
    public List<UserResponseDTO> getAllUser() { //to get all the users

       List<User> userList =  userRepository.findAll(); // fill all the users

       List<UserResponseDTO> userResponseList = new ArrayList<>();

       for(User user : userList){
           userResponseList.add(UserEntityDTOMapper.convertUserToUserResponseDTO(user)); //convert to user response
       }
        return userResponseList;
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) { //to update user

       // getting user,if id does not exist throw exception
        User user =  userRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("User does Not Exists with Id "+id));

        //check if the email is already present
        if(!user.getEmail().equals(userRequestDTO.getEmail())){  //if both the email is different,then find if email is already present
            Optional<User> existingUser = userRepository.findByEmail(userRequestDTO.getEmail());

            if(existingUser.isPresent() && !existingUser.get().getId().equals(id)){  //if email is present and both are different user then throw error
                throw new EmailAlreadyExistsException("Email Already Exists!");
            }
        }
        //updating all the fields
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setUserRole(userRequestDTO.getUserRole());

       User savedUser =  userRepository.save(user); //saving to db
       return UserEntityDTOMapper.convertUserToUserResponseDTO(savedUser); //converting to response
    }

    @Override
    public void deleteUser(Long id) {

        //find the user is present or not
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User does Not Exists with Id "+id));

        userRepository.delete(user);
    }

    @Override
    public UserResponseDTO getUserByEmail(String email) {

        //find user is present or not
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("User does not Present with Email "+email));

        return UserEntityDTOMapper.convertUserToUserResponseDTO(user); //returning response
    }
}
