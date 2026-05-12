package com.example.PF.User;

import com.example.PF.exception.UserAlreadyExists;
import com.example.PF.exception.UserNotFound;
import com.example.PF.exception.UsuarioNotAdmin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseUserDTO save(SaveUserDTO saveUserDTO, Integer userId) {

        if(!isAdmin(userId)){
            throw new UsuarioNotAdmin();
        }
        if (userRepository.existsByCpf(saveUserDTO.getCpf())) {
            throw new UserAlreadyExists();
        }
        if (saveUserDTO.getCpf() == null){
            throw  new UserNotFound();
        }
        User user = User.toModel(saveUserDTO);
        user = userRepository.save(user);

        return ResponseUserDTO.toDTO(user);
    }

    public Page<ResponseUserDTO> list(Pageable pageable){

        return userRepository
                .findAll(pageable)
                .map(user -> ResponseUserDTO.toDTO(user));

    }
    public void deleteUser(Integer id, Integer userId){
        if(!isAdmin(userId)){
            throw new UsuarioNotAdmin();
        }
        userRepository.deleteById(id);

    }

    public User get(Integer cpf) {
        return userRepository.findByCpf(cpf)
                .orElseThrow(() -> new UserNotFound());
    }

    public boolean isAdmin(Integer id) {
        try {
            User user = getById(id);
            return Papel.ADMIN.equals(user.getPapel());
        } catch (Exception e) {
            return false;
        }
    }

    public User getById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound());
    }

    public ResponseUserDTO edit(Integer cpf, EditUserDTO editUserDTO, Integer userId) {
        if(!isAdmin(userId)){
            throw new UsuarioNotAdmin();
        }
        User userDB = get(cpf);

        userDB.setNome(editUserDTO.getNome());
        userDB.setCpf(editUserDTO.getCpf());

        userDB = userRepository.save(userDB);
        return ResponseUserDTO.toDTO(userDB);
    }
}
