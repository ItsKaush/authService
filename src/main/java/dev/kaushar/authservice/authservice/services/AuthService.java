package dev.kaushar.authservice.authservice.services;


import dev.kaushar.authservice.authservice.Dtos.UserDto;
import dev.kaushar.authservice.authservice.Dtos.ValidateSessionResponseDto;
import dev.kaushar.authservice.authservice.exceptions.IncorrcetPasswordException;
import dev.kaushar.authservice.authservice.exceptions.TokenNotFoundException;
import dev.kaushar.authservice.authservice.exceptions.UserAlreadyExistsException;
import dev.kaushar.authservice.authservice.exceptions.UserNotFoundException;
import dev.kaushar.authservice.authservice.models.Session;
import dev.kaushar.authservice.authservice.models.SessionStatus;
import dev.kaushar.authservice.authservice.models.User;
import dev.kaushar.authservice.authservice.repositories.RoleRepository;
import dev.kaushar.authservice.authservice.repositories.SessionRepository;
import dev.kaushar.authservice.authservice.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private RoleRepository roleRepository;

    public AuthService(SessionRepository sessionRepository, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder){
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto signUp(String name, String email, String password) throws UserAlreadyExistsException {
        //check if incoming email id exists
        Optional<User> optionalUser = userRepository.findUserByEmailId(email);

        if(optionalUser.isPresent()){
            throw new UserAlreadyExistsException("User with email " + email + "Already exists");
        }

        //If user does not exist then create a new user and save it
        User user = new User();
        user.setName(name);
        user.setEmailId(email);

        //Encrypt the password before storing it
        user.setPassword(passwordEncoder.encode(password));

        User savedUser = userRepository.save(user);

        return UserDto.from(savedUser);
    }

    public ResponseEntity<UserDto> signIn(String email, String password) throws UserNotFoundException, IncorrcetPasswordException {
        //check if user exists with incoming email
        Optional<User> optionalUser = userRepository.findUserByEmailId(email);

        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User with email Id "+ email + "does not exists!");
        }

        //If exists then check if  incoming password matches with the stored in db
        User user = optionalUser.get();
        if (!passwordEncoder.matches(password, user.getPassword())){
            throw new IncorrcetPasswordException("Incorrect password! Please check");
        }


        //If password matches then generate a random token and store to the db
        String token = RandomStringUtils.randomAscii(20);
        Session session = new Session();
        session.setToken(token);
        session.setStatus(SessionStatus.ACTIVE);
        Date date = new Date();
        long expiredPeriod = date.getTime()+(long)30*24*60*60*1000;
        session.setExpiredAt(new Date(expiredPeriod));
        session.setUser(user);

        sessionRepository.save(session);

        MultiValueMap<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add("AUTH_TOKEN", token);

        return new ResponseEntity<>(UserDto.from(user)
                                        , headers
                                        , HttpStatus.OK);

    }

    public Optional<UserDto> validate(Long userId, String token) throws TokenNotFoundException {
        //if token does not exists in database then token is invalid
        Optional<Session> optionalSession = sessionRepository.findByTokenAndUser_Id(token, userId);

        if(optionalSession.isEmpty()){
            return Optional.empty();
        }

        Session session = optionalSession.get();

        if(session.getExpiredAt().before(new Date())){
            //change the status to expired
            sessionRepository.updateStatusById(SessionStatus.EXPIRED, session.getId());
            return Optional.empty();
        }

        if(!session.getStatus().equals(SessionStatus.ACTIVE)){
            return Optional.empty();
        }

        User user = userRepository.findUserById(userId).get();
        UserDto userDto = UserDto.from(user);

        return Optional.of(userDto);

    }

    public ResponseEntity<Void> logOut(Long userId, String token){
        Optional<Session> optionalSession = sessionRepository.findByTokenAndUser_Id(token,userId);

        if(optionalSession.isEmpty()){
            return null;
        }

        Session session = optionalSession.get();
        session.setStatus(SessionStatus.LOGGEDOUT);

        sessionRepository.save(session);

        return ResponseEntity.ok().build();

    }


}


















