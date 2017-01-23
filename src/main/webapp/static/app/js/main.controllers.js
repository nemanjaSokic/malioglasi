var malioglasi = angular.module('malioglasi.controllers', ['ngCookies']);

malioglasi.controller('HomeController', function($scope, $cookies, $http, $location, $routeParams, homeService) {

$scope.getAll = function() {
        homeService.getAll($scope.page, $scope.direction,$scope.property)
				.success(function(data,status,headers) {
					$scope.categories = data;
				})
				.error(function() {
				});
	};

$scope.getAdsAll = function() {
        homeService.getAdsAll($scope.page, $scope.direction,$scope.property, $scope.filtCategory, $scope.filtDate)
				.success(function(data,status,headers) {
					$scope.ads = data;
				})
				.error(function() {
				});
	};
    
    $scope.changeDir = function(){
        if($scope.direction === "desc"){
            $scope.direction = "asc";
        }else{
            $scope.direction = "desc";
        }
    };

    $scope.dateExpired = function (ad) {
        var currentDate = Date.now();
        var expireDate = ad.expire;
        if(expireDate < currentDate){
            return true;
        } 
    }

    $scope.init = function() {
		$scope.user = {};
        $scope.ad = {};
		if($routeParams.username){
            homeService.getOneUser($routeParams.username)
            .success(function(data){
                $scope.user = data;
            })
            .error(function(){

            });  
        }
		if ($routeParams.id) { 
			homeService.getOneAd($routeParams.id)
					.success(function(data) {
						$scope.ad = data;
					})
					.error(function() {
						
					});
		}
	};



    $scope.logIn = function(){
        homeService.getOneUser($scope.logUser)
            .success(function(data){
                $scope.user = data;

                if($scope.user.approved == false){
                    alert("Administrator have not confirm your account.");
                    return false;
                }

                if($scope.user.password === $scope.logPass && $scope.user.admin == true){
                    $cookies.put('username',$scope.user.userName);
                    $cookies.put('password',$scope.user.password);
                    $location.path("/"+ $scope.user.userName +"/adminhome");
                }else if($scope.user.password === $scope.logPass && $scope.user.admin == false){
                    $cookies.put('username',$scope.user.userName);
                    $cookies.put('password',$scope.user.password);
                    $location.path("/"+ data.userName +"/userhome");
                }else{
                    $scope.alertLogPass=true;
                }

            })
            .error(function(){
                $scope.alertLogUser=true;
            });
    }

    $scope.register = function(user){
        if($scope.user.password != $scope.passAgain){
            $scope.passEx = true;
        }else{
        $scope.user =user;
        homeService.saveUser($scope.user)
            .success(function(data){
                $location.path('/');
            })
            .error(function(){
                $scope.usernameEx = true;
            });
        }
    };

    
    
});

malioglasi.controller('UserController', function($scope, $cookies, $http, $location, $routeParams, userService) {

    $scope.getAll = function() {
        userService.getAll()
				.success(function(data,status,headers) {
					$scope.categories = data;
				})
				.error(function() {
				});
	};

$scope.init = function() {
        $scope.ad = {};
        if($cookies.get('username') == null || $cookies.get('password') == null){
            $location.path("/");
            alert("You are not logged in!");
        }
};

$scope.getUserAds = function() {
        userService.getUserAds($routeParams.username)
				.success(function(data,status,headers) {
					$scope.ads = data;
				})
				.error(function() {
				});
	};

$scope.getOneUser = function(){
    userService.getOneUser($routeParams.username)
        .success(function(data){
            $scope.user = data;
        })
        .error(function(){

        });
    };

$scope.logOut = function(){
        $cookies.remove('username');
        $cookies.remove('password');
        $location.path("/");
    };
    
$scope.saveAd = function(){
        userService.saveAd($scope.ad,$scope.categoryName, $routeParams.username)
            .success(function(data){
                $location.path('/'+data.author.username+'/ads');
            })
            .error(function(){
                $scope.adEx = true;
            });
    };  
    
$scope.deleteAd = function(adId){
    userService.deleteAd(adId)
        .success(function(data){
            $scope.getUserAds();
                })
            .error(function(){
                $scope.adEx = true;
            });
    
};  
    
    $scope.changePass = function(){
        userService.getOneUser($routeParams.username)
        .success(function(data){
            $scope.user = data;

            if($scope.user.password===$scope.oldPass && $scope.newPass == $scope.confPass){
            $scope.user.password = $scope.newPass;
            userService.changePass($routeParams.username,$scope.user)
                .success(function(data){
                    alert("successful")
                })
                .error(function(){

                });
        }else{
            
        }
            
        })
        .error(function(){

        });
    };
         
});

malioglasi.controller('AdminController', function($scope, $cookies, $http, $location, $routeParams, adminService) {

    $scope.init = function() {
        
        if($cookies.get('username') == null || $cookies.get('password') == null){
            $location.path("/");
            alert("You are not logged in!");
        }
};

$scope.getAdsAll = function() {
        adminService.getAdsAll($scope.page, $scope.direction,$scope.property, $scope.filtCategory, $scope.filtDate)
				.success(function(data,status,headers) {
					$scope.ads = data;
				})
				.error(function() {
				});
	};

$scope.getOneAdmin = function(){
    adminService.getOneAdmin($routeParams.username)
        .success(function(data){
            $scope.admin = data;
        })
        .error(function(){

        });
    };

    $scope.logOut = function(){
        $cookies.remove('username');
        $cookies.remove('password');
        $location.path("/");
    };

});