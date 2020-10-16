// Get the hash of the url
const hash = window.location.hash
.substring(1)
.split('&')
.reduce(function (initial, item) {
  if (item) {
    var parts = item.split('=');
    initial[parts[0]] = decodeURIComponent(parts[1]);
  }
  return initial;
}, {});
window.location.hash = '';

// Set token
console.log(hash.code)
let _token = hash.access_token;
_token = hash.code;

const authEndpoint = 'https://accounts.spotify.com/authorize';

// Replace with your app's client ID, redirect URI and desired scopes
const clientId = 'a1b0deda5c8049a5b5c83bea29e3dfb2';
const redirectUri = 'http://localhost:8080/auth';
const scopes = [
  'user-top-read',
  'user-read-email',
  'playlist-modify-private',
  'user-read-private',
  'user-library-modify',
  'streaming',
  'user-read-currently-playing',
  'playlist-modify-public',
  'user-read-playback-state',
  'app-remote-control',
  'user-library-read',
  'user-follow-read',
  'playlist-read-private',
  'user-top-read',
  'user-read-playback-position',
  'user-library-read',
  'playlist-read-collaborative',
 'ugc-image-upload',
 'user-modify-playback-state',
 'user-read-recently-played'
];

// If there is no token, redirect to Spotify authorization
if (!_token) {
  window.location = `${authEndpoint}?client_id=${clientId}&redirect_uri=${redirectUri}&scope=${scopes.join('%20')}&response_type=code&show_dialog=true`;
}

// Make a call using the token
 //callServer(_token);
 console.log(_token);
 callServer(_token);
/*$.ajax({
   url: "https://api.spotify.com/v1/me/top/artists",
   type: "GET",
   beforeSend: function(xhr){xhr.setRequestHeader('Authorization', 'Bearer ' + _token );},
   success: function(data) { 
     // Do something with the returned dat
     data.items.map(function(artist) {
       let item = $('<li>' + artist.name + '</li>');
       item.appendTo($('#top-artists'));
     });
   }
});*/
function callServer(postdata){
var Data = {"token":postdata};
/*$.ajax({
   url: "http://localhost:8080/auth/token",
   type: "POST",
   data:  JSON.stringify ({"token": postdata}),
   contentType: "application/json",
   dataType: 'json',
   beforeSend: function(xhr){
    //xhr.setRequestHeader('Authorization', 'Bearer ' + _token );
   },
   success: function(data) {

   }
});*/
}