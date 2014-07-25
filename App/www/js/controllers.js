angular.module('starter.controllers', [])

.controller('DashCtrl', function($scope) {
})

.controller('UsersCtrl', function($scope, Friends, $location) {
      $scope.friends = Friends.all();
  
      $scope.usersPromise = $scope.Restangular().all("users");
      
      // callback for ng-click 'editUser':
      $scope.editUser = function (userId) {
          $location.path('/user-detail/' + userId);
      }

      // callback for ng-click 'deleteUser':
      $scope.deleteUser = function (userId) {
//        $scope.usersPromise.remove();
          $scope.Restangular().all("users").all(userId).remove();
//        $scope.users = UsersFactory.delete();
          $scope.Restangular().all("users").getList().then(
              function(result) {
  //                console.log(result);
                  $scope.users = result;
              },
              function(resultFail) {
  //                console.log(resultFail);
              }
          );
      }

      // callback for ng-click 'createUser':
      $scope.createNewUser = function () {
          $location.path('/user-creation');
      };

      $scope.usersPromise.getList().then(
          function(result) {
              $scope.users = result;
          },
          function(resultFail) {
//              console.log(resultFail);
          }
      );
})

.controller('UserDetailCtrl', function($scope, $stateParams, Friends) {
  
  
  $scope.Restangular().all("users").get($stateParams.userId).then(
          function(result) {
        	  console.log(result);
              $scope.user = result;
          },
          function(resultFail) {
//              console.log(resultFail);
          }
      );  
})



.controller('secureCtrl', function($scope, Auth, $state) {
   //nothing to see here, move along
   $scope.logOut = function() {
       console.log('loggedout');
       Auth.clearCredentials();
       $state.go('tab.dash',{},{reload: true});
   }
})

.controller('loginCtrl', function($scope, Auth, $state) {
     if($scope.isAuthenticated() === true) {
         //Point 'em to logged in page of app
         $state.go('tab.dash');
     }
     
     //we need to put the salt on server + client side and it needs to be static
     $scope.salt = "nfp89gpe"; //PENDING
     
     $scope.model = {}; 	 
     
     $scope.submit = function() {
         if ($scope.model.userName && $scope.model.passWord) {
             $scope.model.passWordHashed = new String(CryptoJS.SHA512($scope.model.passWord + $scope.model.userName + $scope.salt));
             Auth.setCredentials($scope.model.userName, $scope.model.passWord);
             $scope.loginResultPromise = $scope.Restangular().all("users").getList();    // Gets user 2
             $scope.loginResultPromise.then(function(result) {
                $scope.loginResult = result;
                $scope.loginMsg = "You have logged in successfully! Status 200OK technomumbojumbo";
                $state.go('tab.dash');
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
     }
     
});



