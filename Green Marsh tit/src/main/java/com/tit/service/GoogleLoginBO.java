package com.tit.service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

@Service
public class GoogleLoginBO {
	/* 인증 요청문을 구성하는 파라미터 */
	// client_id: 애플리케이션 등록 후 발급받은 클라이언트 아이디
	// response_type: 인증 과정에 대한 구분값. code로 값이 고정돼 있습니다.
	// redirect_uri: 네이버 로그인 인증의 결과를 전달받을 콜백 URL(URL 인코딩). 애플리케이션을 등록할 때 Callback
	// URL에 설정한 정보입니다.
	// SCOPE = 요청할 권한의 범위를 지정하는 값입니다. 구글 API에 따라 다르게 설정할 수 있습니다.
	
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private final static String CLIENT_ID = "22641404320-61a62g9r725i4erqeqrnlhetivm8s95m.apps.googleusercontent.com";
	private final static String CLIENT_SECRET = "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";
	private final static String REDIRECT_URI = "http://localhost:8080/oauth2/google/callback";
	private final static String SCOPE = "openid email profile";
	
	public String getAuthorizationUrl(HttpSession session) {
        GoogleClientSecrets.Details web = new GoogleClientSecrets.Details();
        web.setClientId(CLIENT_ID);
        web.setClientSecret(CLIENT_SECRET);

        GoogleClientSecrets clientSecrets = new GoogleClientSecrets().setWeb(web);

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets,
                Arrays.asList(SCOPE.split(" "))).setAccessType("offline").setApprovalPrompt("force").build();

        String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).setState("state").build();
        return url;
    }

    public String[] getUserProfile(String authCode) throws IOException {
        GoogleClientSecrets.Details web = new GoogleClientSecrets.Details();
        web.setClientId(CLIENT_ID);
        web.setClientSecret(CLIENT_SECRET);

        GoogleClientSecrets clientSecrets = new GoogleClientSecrets().setWeb(web);

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets,
                Arrays.asList(SCOPE.split(" "))).setAccessType("offline").setApprovalPrompt("force").build();

        GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(HTTP_TRANSPORT, JSON_FACTORY,
                CLIENT_ID, CLIENT_SECRET, authCode, REDIRECT_URI).execute();

        GoogleIdToken idToken = tokenResponse.parseIdToken();
        GoogleIdToken.Payload payload = idToken.getPayload();
        
        String email = payload.getEmail();
        String name = (String) payload.get("name");
        String id = payload.getSubject(); // 사용자 아이디를 가져옵니다.
        
        return new String[]{email, name, id};
    }
    
 // 회원 탈퇴
        public void revokeToken(String accessToken) throws IOException {
            URL url = new URL("https://oauth2.googleapis.com/revoke?token=" + accessToken);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.getResponseCode(); // 응답 코드를 읽어야 API 요청이 발생합니다.
        }
    }