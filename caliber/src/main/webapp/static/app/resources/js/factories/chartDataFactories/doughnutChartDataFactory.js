angular.module("reportApi").factory(
		"doughnutChartDataFactory",
		function($http, $log) {
			$log.debug("Booted doughnutChartData Factory");

			var report = {};

			report.batchWeekQCPie = function(batchId, weekNum) {
				return $http({
					url : "/all/reports/batch/" + batchId + "/week/" + weekNum
							+ "/pie",
					method : "GET"
				}).then(function(response) {
					$log.debug("Batch -> Week -> Pie Chart");
					$log.debug(response);
					return response.data;
				}, function(response) {
					$log.error("There was an error in doughnutChartDataFactory -> batchWeekQCPie. " + response.status);
					}
				);
			};
			return report;
		});
