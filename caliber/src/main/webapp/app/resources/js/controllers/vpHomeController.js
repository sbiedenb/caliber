angular.module("vp").controller(
    "vpHomeController",
    function ($scope, $log, caliberDelegate, chartsDelegate, allBatches) {
        $log.debug("Booted vp home controller.");
        $log.debug(allBatches);

        (function start(){
            createDefaultCharts();
        })();

        function createDefaultCharts(){

            NProgress.start();
            caliberDelegate.agg.getAggTechAllBatch()
                .then(function(data){
                    NProgress.done();
                    var radarChartObject =  chartsDelegate.radar.getAllBatchRankComparisonChart(data);
                    $scope.radarData = radarChartObject.data;
                    $scope.radarLabels = radarChartObject.labels;
                    $scope.radarSeries = radarChartObject.series;
                    $scope.radarOptions = radarChartObject.options;
                });
        }

        /*********************************************** UI ***************************************************/
        var viewCharts = 0;

        $scope.batches = allBatches;
        $scope.currentBatch = {trainingName: "Batch"};
        $scope.currentTrainee = {name: "Trainee"};

        // on batch selection
        $scope.selectCurrentBatch = function (index) {
            $scope.currentTrainee = {name: "Trainee"};
            // turn of batches
            if (index === -1) {
                viewCharts = 0;
                $scope.currentBatch = {trainingName: "Batch"};
                createDefaultChart();
            }
            else {
                $scope.currentBatch = $scope.batches[index];
                viewCharts = 1;
                createBatchCharts();
            }
        };

        // on trainee selection
        $scope.selectCurrentTrainee = function (index) {
            if (index === -1) {
                $scope.currentTrainee = {name: "Trainee"};
                viewCharts = 1;
                createBatchCharts();
            }
            else {
                $scope.currentTrainee = $scope.currentBatch.trainees[index];
                viewCharts = 3;
                createTraineeCharts();
            }
        };

        // hide filter tabs
        $scope.hideOtherTabs = function () {
            return $scope.currentBatch.trainingName !== "Batch";
        };

        // show charts
        $scope.showCharts = function (charts) {
            return charts === viewCharts;
        };

        /************************************** Chart Creation Functions *************************************/

        // create charts on batch selection
        function createBatchCharts() {
            NProgress.start();
            caliberDelegate.agg.getAggTechBatch($scope.currentBatch.batchId)
                .then(function(data){
                    var radarChartObject = chartsDelegate.radar.getBatchRankComparisonChart(data);
                    $scope.radarData = radarChartObject.data;
                    $scope.radarLabels = radarChartObject.labels;
                    $scope.radarSeries = radarChartObject.series;
                    $scope.radarOptions = radarChartObject.options;
                });

            caliberDelegate.agg.getAggWeekBatch($scope.currentBatch.batchId)
                .then(function(data){
                    NProgress.done();
                    var lineChartObject = chartsDelegate.line.getBatchProgressChart(data);
                    $scope.batchProgressLabels = lineChartObject.labels;
                    $scope.batchProgressData = lineChartObject.data;
                    $scope.batchProgressSeries = lineChartObject.series;
                    $scope.batchProgressOptions = lineChartObject.options;
                    $scope.batchProgressDatasetOverride = lineChartObject.datasetOverride;
                });
        }

        // create charts on tech selection
        function createTechCharts() {

            var sample4 = [{trainee: "Rikki", average: ranNum()},
                {trainee: "Kyle", average: ranNum()},
                {trainee: "Osher", average: ranNum()},
                {trainee: "Danny P", average: ranNum()},
                {trainee: "Bryan", average: ranNum()},
                {trainee: "Brayn", average: ranNum()},
                {trainee: "Thomas", average: ranNum()},
                {trainee: "Mark", average: ranNum()},
                {trainee: "Michael", average: ranNum()}];

            var techBarData = chartsDelegate.hbar.getBatchTechEvalChart(sample4);
            $scope.batchTechLabels = techBarData.labels;
            $scope.batchTechData = techBarData.data;
            $scope.batchTechSeries = techBarData.series;
        }

        // create charts on trainee selection
        function createTraineeCharts() {

            NProgress.start();
            caliberDelegate.agg.getAggWeekTrainee($scope.currentTrainee.traineeId)
                .then(function(data){
                    $log.debug(data);
                    NProgress.done();
                    var lineChartObject = chartsDelegate.line.getTraineeProgressChart(data);
                    $scope.lineLabels = lineChartObject.labels;
                    $scope.lineSeries = lineChartObject.series;
                    $scope.lineData = lineChartObject.data;
                    $scope.lineDatasetOverride = lineChartObject.datasetOverride;
                    $scope.lineOptions = lineChartObject.options;
                });

            caliberDelegate.agg.getAggTechTrainee($scope.currentTrainee.traineeId)
                .then(function(data){
                    $log.debug(data);
                    var radarChartObject = chartsDelegate.radar.getTraineeTechProgressChart(data);
                    $scope.radarData = radarChartObject.data;
                    $scope.radarLabels = radarChartObject.labels;
                    $scope.radarSeries = radarChartObject.series;
                    $scope.radarOptions = radarChartObject.options;
                });
        }
    });