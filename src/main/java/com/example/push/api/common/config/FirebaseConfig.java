package com.example.push.api.common.config;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.InputStream;


@Configuration
public class FirebaseConfig {
    private final Logger logger = LoggerFactory.getLogger(FirebaseConfig.class);

    @Value("${sdk.path}")
    private String[] sdkArray;

    // 서버 최초실행 시 한번 실행하려 했으나, 추후에 서버를 내렸다가 올려야하므로 호출방식으로 변경
    //@PostConstruct
    public void initialize() throws Exception
    {
        try {

            for (int i = 0; i < sdkArray.length; i++) {
                ClassPathResource resource1 = new ClassPathResource(sdkArray[i]);
                InputStream serviceAccount1 = resource1.getInputStream();

                FirebaseOptions options1 = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount1))
                        .build();

                FirebaseApp init = FirebaseApp.initializeApp(options1, sdkArray[i]);
                logger.info("using_project " + i + " " + options1);
                logger.info(init.toString());
            }


        } catch (Exception e) {
            logger.error("Firebase ServiceAccountKey FileNotFoundException" + e.getMessage());
        }

    }

    public FirebaseApp init(String path) throws Exception{
        FirebaseApp nm = FirebaseApp.getInstance(path);

        return nm;
    }

}
