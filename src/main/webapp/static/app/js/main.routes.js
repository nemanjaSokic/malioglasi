var malioglasi = angular.module('malioglasi.routes', ['ngRoute']);

malioglasi.config(['$routeProvider', function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl : '/static/app/html/partial/home.html',
            	controller : 'HomeController'
        })
        .when('/categories', {
            templateUrl : '/static/app/html/partial/categories.html',
            	controller : 'HomeController'
        })
        .when('/registration', {
            templateUrl : '/static/app/html/partial/registration.html',
            	controller : 'HomeController'
        })
        .when('/ads', {
            templateUrl : '/static/app/html/partial/ads.html',
            	controller : 'HomeController'
        })
        .when('/ads/view/:id', {
            templateUrl : '/static/app/html/partial/adview.html',
            	controller : 'HomeController'
        })
        .when('/:username/adminhome', {
            templateUrl : '/static/app/html/partial/adminHome.html',
            	controller : 'AdminController'
        })
        .when('/:username/userhome', {
            templateUrl : '/static/app/html/partial/userHome.html',
            	controller : 'UserController'
        })
        .when('/:username/ads', {
            templateUrl : '/static/app/html/partial/userAds.html',
            	controller : 'HomeController'
        })
        .when('/:username/create_ad', {
            templateUrl : '/static/app/html/partial/createAdd.html',
            	controller : 'UserController'
        })
        .when('/:username/account', {
            templateUrl : '/static/app/html/partial/userAccount.html',
            	controller : 'UserController'
        })
        .when('/admin/authors', {
            templateUrl : '/static/app/html/partial/adminAuthors.html',
            	controller : 'AdminController'
        })        
        .otherwise({
            redirectTo: '/'
        });
}]);