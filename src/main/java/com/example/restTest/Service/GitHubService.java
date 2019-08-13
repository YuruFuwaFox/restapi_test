package com.example.restTest.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.github.api.GitHub;
import org.springframework.social.github.api.GitHubUserProfile;
import org.springframework.social.github.api.impl.GitHubTemplate;
import org.springframework.social.github.connect.GitHubConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

@Service
public class GitHubService {

    @Value("${github.client}")
    private String clientId;

    @Value("${github.secret}")
    private String secret;

    @Value("${github.callbackurl}")
    private String callbackUrl;


    public String createGithubAuthorizeUrl(){
        return operations().buildAuthenticateUrl(GrantType.IMPLICIT_GRANT, new OAuth2Parameters());
    }

    public Object getAccessToken(String code){
        AccessGrant accessGrant = operations().exchangeForAccess(code, callbackUrl, null);
        return accessGrant.getAccessToken();
    }

    public GitHubUserProfile getGitHubUserProfile(String accessToken){
        GitHub gitHub = new GitHubTemplate(accessToken);
        return gitHub.userOperations().getUserProfile();
    }

    private OAuth2Operations operations(){
        GitHubConnectionFactory gitHubConnectionFactory = new GitHubConnectionFactory(clientId, secret);
        OAuth2Operations operations = gitHubConnectionFactory.getOAuthOperations();
        return operations;
    }


}
