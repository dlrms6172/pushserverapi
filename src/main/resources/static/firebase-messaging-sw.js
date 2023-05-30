
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries
importScripts('https://www.gstatic.com/firebasejs/8.2.0/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/8.2.0/firebase-messaging.js');
// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional

// push-server-test-sdk
// const firebaseConfig = {
//     apiKey: "AIzaSyDKt7kqWMLYzlIS07PM0EP0VQfIYuboAV4",
//     authDomain: "push-server-test-28616.firebaseapp.com",
//     projectId: "push-server-test-28616",
//     storageBucket: "push-server-test-28616.appspot.com",
//     messagingSenderId: "892251042492",
//     appId: "1:892251042492:web:f5a019a669dc64315216b9",
//     measurementId: "G-2KR1652P3W"
// };


// multiple-sdk
const firebaseConfig = {
    apiKey: "AIzaSyBOwcZiLbZdfb3_1-WOECs5xL5sEptn-EI",
    authDomain: "multiple-bb7d7.firebaseapp.com",
    projectId: "multiple-bb7d7",
    storageBucket: "multiple-bb7d7.appspot.com",
    messagingSenderId: "24465679384",
    appId: "1:24465679384:web:dabfc26452ad7ff88c4f6d",
    measurementId: "G-0489SV026H"
}

// Initialize Firebase
firebase.initializeApp(firebaseConfig);

// Retrieve firebase messaging
// eslint-disable-next-line no-undef
const messaging = firebase.messaging();
// eslint-disable-next-line no-undef


messaging.onBackgroundMessage(function(payload) {
    console.log('[firebase-messaging-sw.js] Received background message ', payload);
    // Customize notification here

    const notificationTitle = 'Background Message Title';
    const notificationOptions = {
        body: 'Background Message body.',
        icon: '/firebase-logo.png'
    };
    self.registration.showNotification(notificationTitle,
        notificationOptions);
});



