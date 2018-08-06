var app = angular.module('app', ['ui.grid','ui.grid.pagination']);

app.controller('ActivityCtrl', ['$scope','ActivityService', function ($scope,ActivityService) {
    var paginationOptions = {
        pageNumber: 1,
        pageSize: 5,
        sort: null
    };

    ActivityService.getActivities(paginationOptions.pageNumber,
        paginationOptions.pageSize).success(function(data){
        $scope.gridOptions.data = data.content;
        $scope.gridOptions.totalItems = data.totalElements;
    });

    $scope.gridOptions = {
        paginationPageSizes: [5, 10, 20],
        paginationPageSize: paginationOptions.pageSize,
        enableColumnMenus:false,
        useExternalPagination: true,
        columnDefs: [
            { name: 'id' },
            { name: 'service' },
            { name: 'action' },
            { name: 'date' }
        ],
        onRegisterApi: function(gridApi) {
            $scope.gridApi = gridApi;
            gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
                paginationOptions.pageNumber = newPage;
                paginationOptions.pageSize = pageSize;
                ActivityService.getActivities(newPage,pageSize).success(function(data){
                    $scope.gridOptions.data = data.content;
                    $scope.gridOptions.totalItems = data.totalElements;
                });
            });
        }
    };

}]);

app.service('ActivityService',['$http', function ($http) {

    function getActivities(pageNumber,size) {
        pageNumber = pageNumber > 0?pageNumber - 1:0;
        return  $http({
            method: 'GET',
            url: '/rest/activity/get?page='+pageNumber+'&size='+size
        });
    }

    return {
        getActivities:getActivities
    };

}]);