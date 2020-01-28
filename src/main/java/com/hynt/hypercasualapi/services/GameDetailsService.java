package com.hynt.hypercasualapi.services;

import com.hynt.hypercasualapi.collections.Game;
import com.hynt.hypercasualapi.repositories.GameRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class GameDetailsService implements UserDetailsService {

    private final GameRepository gameRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public GameDetailsService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        Game game = gameRepository.findGameByName(name);

        if(Optional.ofNullable(game).isPresent()){

            GrantedAuthority authority = new SimpleGrantedAuthority("ADMIN");

            User user = new User(game.getName(),encoder.encode(game.getPassword()), Arrays.asList(authority));

            return user;
        }

        throw new UsernameNotFoundException("Jogo não encontrado.");
    }
}