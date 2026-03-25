export const auth0Config = {
 clientId: 'D6lDUfSroAsbz5qtPS8pdSE85QmppXwk',
 domain: "https://dev-ze776e0gpjt22acb.us.auth0.com",
 audience: "http://localhost:8080",
 redirectUri: window.location.origin+"/callback",
 scope: 'openid profile email read:books'
}