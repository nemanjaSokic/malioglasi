var malioglasi = angular.module('malioglasi.services', []);

malioglasi.service('homeService', function($http) {
	
    this.getAll = function(page,direction,property) {
		return $http.get('api/categories', {params : {'page':page, 'direction':direction, 'property':property} } )};

    this.getAdsAll = function(page,direction,property,filtCategory,filtDate) {
		return $http.get('api/ads', {params : {'page':page, 'direction':direction, 'property':property, 'filtCategory':filtCategory, 'filtDate':filtDate} } )};

    this.getOneAd = function(id) {
		return $http.get('api/ads' + '/' + id);
	};

  this.getOneUser = function(username){
  return $http.get('api/authors/'+ username);
  };

  this.saveUser = function(user){
    return $http.post('api/authors', user)
  };


});

malioglasi.service('userService', function($http) {
  this.getAll = function() {
		return $http.get('api/categories')};

  this.getOneUser = function(username){
  return $http.get('api/authors/'+ username);
  };

  this.getUserAds = function(user) {
	return $http.get('api/'+user+'/ads')};

  this.saveAd = function(ad,categoryName,user){
    return $http.post('api/'+user+'/ads', ad,{params:{'category':categoryName}})
  };  

  this.deleteAd = function(adId){
    return $http.delete('api/ads/'+adId);
  };

  this.changePass = function(username, user){
    return $http.put('api/authors/'+username,user);
  };

    
});

malioglasi.service('adminService', function($http) {
this.getOneAdmin = function(username){
  return $http.get('api/authors/'+ username);
  };

this.getAdsAll = function(page,direction,property,filtCategory,filtDate) {
		return $http.get('api/ads', {params : {'page':page, 'direction':direction, 'property':property, 'filtCategory':filtCategory, 'filtDate':filtDate} } )};


});