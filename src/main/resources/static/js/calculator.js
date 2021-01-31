angular.module('sharp', [])
    .controller('home', function($scope, $http) {
        $scope.hist = {id: 'xxx', content: 'Hello World!'}
        $http.get('/calculator/history').success(function(data) {
            console.log(data)
            // $scope.greeting = data;
            // $scope.operationHistory = {id: 'xxx', content: data}
            $scope.hist = {history: data}
        })
    })