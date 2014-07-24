var myApp = angular.module('starter.controllers', []);



myApp.controller('FriendsCtrl', function($scope, Friends) {
  $scope.friends = Friends.all();
})

myApp.controller('FriendDetailCtrl', function($scope, $stateParams, Friends) {
  $scope.friend = Friends.get($stateParams.friendId);
})

myApp.controller('AccountCtrl', function($scope) {
});

/* Clear browser cache (in development mode)
http://stackoverflow.com/questions/14718826/angularjs-disable-partial-caching-on-dev-machine */
myApp.run(function ($rootScope, $templateCache) {
 $rootScope.$on('$viewContentLoaded', function () {
     $templateCache.removeAll();
 });
});

//The program does not check if your username or password are wrong, but
//it does is call a GET request for user with ID 2. If the GET request is successfuly, you are
//authorizedl, otherwise, you are not authorized. I will add true authrization before I leave.

myApp.controller('loginCtrl', ['$scope', 'Auth', '$state',
function($scope, Auth, $state) {
  if($scope.isAuthenticated() === true) {
      //Point 'em to logged in page of app
      $state.go('secure');
  }
  
  //we need to put the salt on server + client side and it needs to be static
  $scope.salt = "nfp89gpe"; //PENDING
  
  $scope.submit = function() {
      if ($scope.userName && $scope.passWord) {
          $scope.passWordHashed = new String(CryptoJS.SHA512($scope.passWord + $scope.userName + $scope.salt));
          Auth.setCredentials($scope.userName, $scope.passWord);
//          $scope.loginResult = $scope.Restangular.get();
          $scope.loginResultPromise = $scope.Restangular().all("users").getList();    // Gets user 2
          $scope.loginResultPromise.then(function(result) {
             $scope.loginResult = result;
             $scope.loginMsg = "You have logged in successfully! Status 200OK technomumbojumbo";
             $state.go('secure');
          }, function(error) {
             $scope.loginMsg = "Arghhh, matey! Check your username or password.";
             Auth.clearCredentials();
          });
          $scope.userName = '';
          $scope.passWord = '';
      } else if(!$scope.userName && !$scope.passWord) {
          $scope.loginMsg = "You kiddin' me m8? No username or password?";
      } else if (!$scope.userName) {
          $scope.loginMsg = "No username? Tryina hack me?";
          $scope.loginResult = "";
      } else if (!$scope.passWord) {
          $scope.loginMsg = "What? No password!? Where do you think you're going?";
          $scope.loginResult = "";
      }
  };
}]);

myApp.controller('secureCtrl', ['$scope', 'Auth', '$state',
function($scope, Auth, $state) {
   //nothing to see here, move along
   $scope.logOut = function() {
       console.log('loggedout');
       Auth.clearCredentials();
       $state.go('secure',{},{reload: true});
   }
}]);

myApp.controller('UserListCtrl', ['$scope', 'UsersFactory', 'UserFactory', '$location',
 function ($scope, UsersFactory, UserFactory, $location) {

     $scope.usersPromise = $scope.Restangular().all("users");
     
     // callback for ng-click 'editUser':
     $scope.editUser = function (userId) {
         $location.path('/user-detail/' + userId);
     };

     // callback for ng-click 'deleteUser':
     $scope.deleteUser = function (userId) {
//       $scope.usersPromise.remove();
         $scope.Restangular().all("users").all(userId).remove();
//       $scope.users = UsersFactory.delete();
         $scope.Restangular().all("users").getList().then(
             function(result) {
 //                console.log(result);
                 $scope.users = result;
             },
             function(resultFail) {
 //                console.log(resultFail);
             }
         );
     };

     // callback for ng-click 'createUser':
     $scope.createNewUser = function () {
         $location.path('/user-creation');
     };

     $scope.usersPromise.getList().then(
         function(result) {
             console.log(result);
             $scope.users = result;
         },
         function(resultFail) {
//             console.log(resultFail);
         }
     );
 }]);

myApp.controller('UserDetailCtrl', ['$scope', '$stateParams', 'UserFactory', '$location',
 function ($scope, $stateParams, UserFactory, $location) {

     // callback for ng-click 'updateUser':
     $scope.updateUser = function () {
         $scope.id = $stateParams.id;
         console.log($scope.user);
         $scope.editUserDetail = $scope.Restangular().one("users").post($scope.id, $scope.user);
         $location.path('/user-list');
     };

     // callback for ng-click 'cancel':
     $scope.cancel = function () {
         $location.path('/user-list'); //this should use state.go
     };

     $scope.Restangular().all("users").get($stateParams.id).then(
         function(result) {
//             console.log(result);
             $scope.user = result;
         },
         function(resultFail) {
//             console.log(resultFail);
         }
     );
 }]);

myApp.controller('UserCreationCtrl', ['$scope', 'UsersFactory', '$location',
 function ($scope, UsersFactory, $location) {

//     callback for ng-click 'createNewUser':
//     $scope.createNewUser = function () {
//         UsersFactory.create($scope.user);
//         $location.path('/user-list');
//     }
     
     // Need to finish the createNewUser function.
     // It checks if all fields are entered. For testing purposes, I am using a default new user.
     // I know a GET request could be easlily implemented, but a POST request gives me issues.
     // The server sides has issues parsing the JSON object
     $scope.createNewUser = function()
     {
         if ($scope.user.username && $scope.user.password && $scope.user.firstName
                 && $scope.user.lastName)
         {

             // console.log($scope.newUser);
             $scope.jsonNewUser = angular.toJson($scope.newUser, false); // convert to JSON
             // console.log($scope.jsonNewUser);
             
         // http:localhost:8080/services/users
         // Sends a POST request to the server
         // Outputs a success or failure message.
         $scope.createNewUserResultPromise = $scope.Restangular().all("users")
                 .post($scope.user);
             
             $scope.createNewUserResultPromise.then(
                 function(result){
                     $scope.creationMessage="User was created!"
                 },
                 function(error){
                     $scope.creationMessage="The darn restangular POST function does not work .-."    
                 }
             );
             
         }
         else {
             $scope.creationMessage = "Remember to fill in everything!";
         }
     }
     
 }]);