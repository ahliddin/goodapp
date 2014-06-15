var goodApp = angular.module("AngularGoodApp", []);

goodApp.controller("SpeakupCtrl", function($scope, $log, $window, $http) {
	$scope.submitForm = function(feedback) {

		if (feedback["spam"] == true)
			feedback["spam"] = 1;
		else
			feedback["spam"] = 0;

		for ( var i in feedback) {
			$log.log(i + " " + feedback[i]);
		}
		$log.log (JSON.stringify(feedback));

		$http.post("feedbacks", feedback).success(function() {
			cleanForm();
			$scope.showfields = false;

		}).error(function() {
			$log.log("add feedback error");
		});


	};

	function cleanForm() {
		$scope.feedback = {};
		$scope.feedback.name = "";
		$scope.feedback.email = "";
		$scope.feedback.comment = "";
		$scope.feedback.spam = false;
	}
	cleanForm();
	$scope.showfields = true;

});

goodApp.controller("FeedbacksCtrl", function($scope, $http) {
	$scope.fetchFeedbacks = function() {
		$http.get("feedbacks").success(function(feedbacks) {
			$scope.feedbacks = feedbacks;
		}).error(function() {
			$log.log("error while fetching feedbacks");
		});
	};

	$scope.fetchFeedbacks();
});