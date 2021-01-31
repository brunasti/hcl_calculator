angular.module('sharp', [])
    .controller('calcHistory', function($scope, $http) {
        console.log("H-1")
        $http.get('/calculator/history').success(function(data) {
            console.log("H-2")
            console.log(data)
            $scope.hist = {history: data}
            console.log("H-3")
        })
    })