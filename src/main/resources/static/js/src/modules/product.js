(function () {
    var productModule = angular.module('product-module', ['ngRoute']);

    var productListCrtl = function ($scope, $http, $location) {

        this.new = function () {
            $location.path('/product/new');
        };

        this.view = function (id) {
            $location.path('/product/view/' + id);
        };

        this.edit = function (id) {
            $location.path('/product/edit/' + id);
        };

        this.select = function (product) {
            this.selected = product;
        };

        this.delete = function () {
            var url = window.location.origin + "/api/stationary-store/" + this.selected.id
            $http
                    .delete(url)
                    .then(function onSuccess(response) {
                        refreshList();
                    });
        };

        var refreshList = function () {
            var url = window.location.origin + "/api/stationary-store";
            $http.get(url)
                    .then(function onSuccess(response) {
                        $scope.productList = response.data;
                    });
        };

        refreshList();
    };

     var productEnrollCrtl = function ($scope, $http, $location, $routeParams) {
        var id = $routeParams.id;

        if (id) {
            var url = window.location.origin + "/api/stationary-store/"+id

            $http.get(url)
                    .then(function onSuccess(response) {
                        $scope.products.productCandidate = response.data;
                    });
        } else {
            this.productCandidate = {};
        }

        this.back = function () {
            $location.path('/stationary-store');
        };

        this.save = function () {
            var url = window.location.origin + "/api/stationary-store"

            $http
                    .post(url, this.productCandidate)
                    .then(function onSuccess(response) {
                        $location.path('/product/view/' + response.data.id);
                    });

        };
    };

    var productViewCrtl = function ($scope, $http, $location, $routeParams) {
        this.productView = {};

        this.back = function () {
            $location.path('/stationary-store');
        };

        var id = $routeParams.id;
        var url = window.location.origin + "/api/stationary-store/"+id

        $http.get(url)
                .then(function onSuccess(response) {
                    $scope.products.productView = response.data;
                });
    };

    productModule.$inject = ['$scope', '$http', '$location', '$routeParams'];
    productModule.controller('ProductListController', productListCrtl);
    productModule.controller('ProductEnrollController', productEnrollCrtl);
    productModule.controller('ProductViewController', productViewCrtl);
})();


