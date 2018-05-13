package ru.cmo.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.cmo.edu.data.UserInfo;
import ru.cmo.edu.data.entity.BaseUser;
import ru.cmo.edu.data.entity.Credentials;
import ru.cmo.edu.data.entity.enumerable.CredentialsTypeEnum;
import ru.cmo.edu.data.repository.CredentialsRepository;
import ru.cmo.edu.data.repository.EduRepository;
import ru.cmo.edu.data.repository.MunicipalityRepository;
import ru.cmo.edu.data.repository.RegionRepository;
import ru.cmo.edu.rest.security.AccessRights;
import ru.cmo.edu.rest.security.Role;

import java.util.EnumSet;
import java.util.List;

/**
 * Created by to on 08.06.2017.
 */
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final CredentialsRepository credentialsEntityRepository;
    private final EduRepository eduRepository;
    private final MunicipalityRepository municipalityRepository;
    private final RegionRepository regionRepository;

    @Autowired
    public JpaUserDetailsService(CredentialsRepository credentialsEntityRepository,
                                 EduRepository eduRepository,
                                 MunicipalityRepository municipalityRepository,
                                 RegionRepository regionRepository) {
        this.credentialsEntityRepository = credentialsEntityRepository;
        this.eduRepository = eduRepository;
        this.municipalityRepository = municipalityRepository;
        this.regionRepository = regionRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Credentials credentials = credentialsEntityRepository.findByLogin(s);
        if (credentials == null) {
            throw new UsernameNotFoundException("could not find the user: " + s);
        } else {
            CredentialsTypeEnum credentialsType = CredentialsTypeEnum.valueOf(credentials.getLoginType());
            List<GrantedAuthority> authorities = null;
            int accessRights = 0;
            BaseUser user = new BaseUser();
            switch (credentialsType) {
                case REGION:
                    authorities = AuthorityUtils.createAuthorityList(String.valueOf(Role.region));
                    user = regionRepository.findById(credentials.getRefId());
                    accessRights = AccessRights.EduForms |
                            AccessRights.MunitForms |
                            AccessRights.RegionForms |
                            AccessRights.Dictionaries |
                            AccessRights.Queries |
                            AccessRights.CreateQueries |
                            AccessRights.Statistics |
                            AccessRights.UploadForm;
                    break;
                case MINISTRY:
                    authorities = AuthorityUtils.createAuthorityList(String.valueOf(Role.ministry));
                    user = new BaseUser();
                    user.setName(StringUtils.isEmpty(credentials.getAlias()) ? BaseUser.MINISTRY_ALIAS : credentials.getAlias());
                    accessRights = AccessRights.EduForms |
                            AccessRights.MunitForms |
                            AccessRights.RegionForms |
                            AccessRights.Queries |
                            AccessRights.Statistics;
                    break;
                case MUNICIPALITY:
                    authorities = AuthorityUtils.createAuthorityList(String.valueOf(Role.municipality));
                    user = municipalityRepository.findById(credentials.getRefId());
                    accessRights = AccessRights.EduForms |
                            AccessRights.MunitForms |
                            AccessRights.Queries |
                            AccessRights.Statistics |
                            AccessRights.UploadForm |
                            AccessRights.Passport |
                            AccessRights.SendMessage;
                    break;
                case EDU:
                    authorities = AuthorityUtils.createAuthorityList(String.valueOf(Role.edu));
                    user = eduRepository.findById(credentials.getRefId());
                    accessRights = AccessRights.EduForms |
                            AccessRights.UploadForm |
                            AccessRights.Passport |
                            AccessRights.SendMessage;
                    break;
            }
            return new UserInfo(credentials.getLogin(), credentials.getPasswd(), user.getName(), user.getId(), authorities, accessRights);
        }
    }
}
