package com.example.restTest.Controller;

import com.example.restTest.Service.GitHubService;
import org.springframework.social.github.api.GitHubUserProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class OAuthController {

    private static final String TOKEN = "token";

    private final GitHubService gitHubService;

    private final HttpSession httpSession;

    public OAuthController(GitHubService gitHubService, HttpSession httpSession){
        this.gitHubService = gitHubService;
        this.httpSession = httpSession;
    }

    @GetMapping("/")
    public String login(){
        return "login";
    }

    @GetMapping("/oauth/login")
    public String github(){
        return "redirect:" + gitHubService.createGithubAuthorizeUrl();
    }

    @GetMapping("/oauth/token")
    public String token(@RequestParam(value = "code", required = false) String code){
        if (code == null){
            return "error/400";
        }
        httpSession.setAttribute(TOKEN, gitHubService.getAccessToken(code));
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String index(Model model){
        if(httpSession.getAttribute(TOKEN) == null){
            return "error/401";
        }
        String accessToken = httpSession.getAttribute(TOKEN).toString();
        GitHubUserProfile userProfile = gitHubService.getGitHubUserProfile(accessToken);
        model.addAttribute("userProfile", userProfile);
        return "index";
    }

    @GetMapping("/logout")
    public String logout(){
        httpSession.invalidate();
        return "redirect:/";
    }

}
