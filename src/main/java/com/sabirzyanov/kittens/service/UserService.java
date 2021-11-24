package com.sabirzyanov.kittens.service;

import com.sabirzyanov.kittens.domain.Cat;
import com.sabirzyanov.kittens.domain.Role;
import com.sabirzyanov.kittens.domain.User;
import com.sabirzyanov.kittens.repository.CatRepo;
import com.sabirzyanov.kittens.repository.UserRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Value("${upload.path}")
    private String uploadPath;

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepository;
    private final CatRepo catRepo;
    private final HttpServletRequest request;

    public UserService(PasswordEncoder passwordEncoder, UserRepo userRepository, CatRepo catRepo, HttpServletRequest request) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.catRepo = catRepo;
        this.request = request;
    }

    public String encodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean addUser(User user, Model model) {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.addAttribute("usernameError", "Пользователь с таким username уже существует");
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(encodedPassword(user.getPassword()));

        userRepository.save(user);

        return true;
    }

    public User findUser(String username){
        if (userRepository.findByUsername(username) == null)
            return null;
        return userRepository.findByUsername(username);
    }

    public void addCat(User user, String catName, MultipartFile file) throws IOException {
        Cat cat;
        String realPath = request.getServletContext().getRealPath(uploadPath);
        if (file != null) {
            File uploadDir = new File(realPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String fileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(realPath + "/" + fileName));
            cat = new Cat(catName, userRepository.findByUsername(user.getUsername()), fileName);
        } else
            cat = new Cat(catName, userRepository.findByUsername(user.getUsername()));

        catRepo.save(cat);
    }

    public List<Cat> findCatsByUser(User user) {
        return catRepo.findAllByUser(user);
    }
}
