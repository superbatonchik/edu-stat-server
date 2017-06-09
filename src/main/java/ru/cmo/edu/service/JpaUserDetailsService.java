package ru.cmo.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.cmo.edu.data.entity.CredentialsEntity;
import ru.cmo.edu.data.entity.enumerable.CredentialsTypeEnum;
import ru.cmo.edu.data.repository.CredentialsEntityRepository;

import java.util.List;

/**
 * Created by to on 08.06.2017.
 */
@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    CredentialsEntityRepository credentialsEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        CredentialsEntity credentials = credentialsEntityRepository.findByLogin(s);
        if (credentials != null) {
            CredentialsTypeEnum credentialsType = CredentialsTypeEnum.get(credentials.getLoginType());
            List<GrantedAuthority> authorities = null;
            switch (credentialsType) {
                case admin:
                    authorities = AuthorityUtils.createAuthorityList("ADMIN");
                    break;
                case ministry:
                    authorities = AuthorityUtils.createAuthorityList("MINISTRY");
                    break;
                case municipality:
                    authorities = AuthorityUtils.createAuthorityList("MUNICIPALITY");
                    break;
                case edu:
                    authorities = AuthorityUtils.createAuthorityList("EDU");
                    break;
            }
            return new User(credentials.getLogin(), credentials.getPasswd(), authorities);
        }
        throw new UsernameNotFoundException("could not find the user: " + s);
    }
}
