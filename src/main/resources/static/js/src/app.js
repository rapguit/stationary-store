(function(){
   var app = angular.module('stationary-store-client', ['product-module', 'ngRoute']);

   var routesConfig = function ($routeProvider) {

              $routeProvider
                      .when('/products', {
                          controller: 'ProductListController',
                          controllerAs: 'products',
                          templateUrl: 'products/list.html'
                      })
                      .when('/product/new', {
                          controller: 'ProductEnrollController',
                          controllerAs: 'products',
                          templateUrl: 'products/form.html'
                      })
                      .when('/product/view/:id', {
                          controller: 'ProductViewController',
                          controllerAs: 'products',
                          templateUrl: 'products/view.html'
                      })
                      .when('/product/edit/:id', {
                          controller: 'ProductEnrollController',
                          controllerAs: 'products',
                          templateUrl: 'products/form.html'
                      })
                      .otherwise({ redirectTo: '/products' });

          };


      app.config(routesConfig);
})();


