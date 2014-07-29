angular
		.module('starter.controllers', [ 'restangular' ])

		.controller('DashCtrl', function($scope) {
		})

		.controller(
				'ModalCtrl',
				function($scope, Restangular) {

					$scope.User = {};

					// callback for ng-click 'deleteUser':
					$scope.createUser = function() {

						if ($scope.User.username && $scope.User.password) {
							console.log('Create User', $scope.User);

							$scope.createNewUserResultPromise = Restangular
									.all("users").post($scope.User).then(
											function(users) {

												$scope.modal.hide();

											}, function(resultFail) {
												// console.log(resultFail);
											});

						}

						else {
							$scope.creationMessage = "Remember to fill in everything!";
						}

					}
				})

		.controller(
				'UsersCtrl',
				function($scope, $location, $ionicModal) {

					$scope.Restangular().all("users").getList().then(
							function(result) {
								$scope.$parent.users = result;
							}, function(resultFail) {
								// console.log(resultFail);
							});

					// callback for ng-click 'editUser':
					$scope.editUser = function(userId) {
						$location.path('/user-detail/' + userId);
					}

					// callback for ng-click 'deleteUser':
					$scope.deleteUser = function(userId) {

						$scope
								.Restangular()
								.all("users")
								.all(userId)
								.remove()
								.then(
										function(users) {
											$scope
													.Restangular()
													.all('users')
													.getList()
													.then(
															function(result) {

																$scope.$parent.users = result;

															},
															function(resultFail) {
																// console.log(resultFail);
															});
										});

					}

					// callback for ng-click 'modal'- open Modal dialog to
					// create new User
					$ionicModal.fromTemplateUrl('modal.html', {
						scope : $scope,
						animation : 'slide-in-up'
					}).then(function(modal) {
						$scope.modal = modal;
					});

					// Execute action on hide modal
					$scope.$on('modal.hidden', function() {
						$scope.Restangular().all('users').getList().then(
								function(result) {									
									$scope.users = result;									
								}, function(resultFail) {
									// console.log(resultFail);
								});
					});

				})

		.controller(
				'UserDetailCtrl',
				function($scope, $stateParams) {

					$scope.Restangular().all("users").get($stateParams.userId)
							.then(function(result) {
								console.log(result);
								$scope.user = result;
							}, function(resultFail) {
								// console.log(resultFail);
							});
				})

		.controller('secureCtrl', function($scope, Auth, $state) {
			// nothing to see here, move along
			$scope.logOut = function() {
				console.log('loggedout');
				Auth.clearCredentials();
				$state.go('tab.dash', {}, {
					reload : true
				});
			}
		})

		.controller(
				'loginCtrl',
				function($scope, Auth, $state) {
					if ($scope.isAuthenticated() === true) {
						// Point 'em to logged in page of app
						$state.go('tab.dash');
					}

					// we need to put the salt on server + client side and it
					// needs to be static
					$scope.salt = "nfp89gpe"; // PENDING

					$scope.model = {};

					$scope.submit = function() {
						if ($scope.model.userName && $scope.model.passWord) {
							$scope.model.passWordHashed = new String(CryptoJS
									.SHA512($scope.model.passWord
											+ $scope.model.userName
											+ $scope.salt));
							Auth.setCredentials($scope.model.userName,
									$scope.model.passWord);
							console.log($scope);
							$scope.loginResultPromise = $scope.Restangular()
									.all("users").getList(); // Gets user 2
							$scope.loginResultPromise
									.then(
											function(result) {
												$scope.loginResult = result;
												$scope.loginMsg = "You have logged in successfully! Status 200OK technomumbojumbo";
												$state.go('tab.dash');
											},
											function(error) {
												$scope.loginMsg = "Arghhh, matey! Check your username or password.";
												Auth.clearCredentials();
											});
							$scope.userName = '';
							$scope.passWord = '';
						} else if (!$scope.userName && !$scope.passWord) {
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
