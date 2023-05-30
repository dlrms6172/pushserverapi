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

    private String firebaseSdkPath = "first-project.json";
    private String sdkPath = "second-project.json";

    // 서버 최초실행 시 한번 실행
    @PostConstruct
    public void initialize() throws Exception {

        try {

//            for (int i = 0; i < sdkArray.length; i++) {
//                ClassPathResource resource = new ClassPathResource(sdkArray[i]);
//                InputStream serviceAccount = resource.getInputStream();
//
//                FirebaseOptions options = FirebaseOptions.builder()
//                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                        .build();
//
//                FirebaseApp init = FirebaseApp.initializeApp(options);
//                logger.info("using_project " + i + " " + options);
//                logger.info(init.toString());
//            }

            ClassPathResource resource = new ClassPathResource(firebaseSdkPath);
            InputStream serviceAccount = resource.getInputStream();

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp init = FirebaseApp.initializeApp(options);

            logger.info(init.toString());

            ClassPathResource resource1 = new ClassPathResource(sdkPath);
            InputStream serviceAccount1 = resource1.getInputStream();

            FirebaseOptions options1 = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount1))
                    .build();

            FirebaseApp init1 = FirebaseApp.initializeApp(options1,"second");

            logger.info(init1.toString());

        } catch (Exception e) {
            logger.error("Firebase ServiceAccountKey FileNotFoundException" + e.getMessage());
        }

    }

}
