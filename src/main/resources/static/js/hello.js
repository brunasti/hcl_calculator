angular.module('sharp', [])
    .controller('home', function($scope, $http) {
        $scope.greeting = {id: 'xxx', content: 'Hello World!'}
        $http.get('/calculator/history').success(function(data) {
            console.log(data)
            // $scope.greeting = data;
            // $scope.operationHistory = {id: 'xxx', content: data}
            $scope.greeting = {history: data}
        })

    })