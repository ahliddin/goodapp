var goodApp = angular.module('AngularGoodApp', []);

goodApp.controller('SpeakupCtrl', function ($scope) {
	var submitForm = function (feedback) {
		$http.post ('addFeedback', feedback).success (function () {
			cleanForm();
		}).error(function () {
			$log.log("add feedback error");
		});
	};
	
	function cleanForm() {
		$scope.feedback.name = '';
		$scope.feedback.email = '';
		$scope.feedback.comment = '';
		$scope.feedback.spam = false;
	}
	cleanForm();
});

goodApp.controller('FeedbacksCtrl', function ($scope, $http) {
	var fetchFeedbacks = function () {
		$http.get ('fetchFeedbacks').success(function (feedbacks) {
			$scope.feedbacks = feedbacks;
		}).error (function () {
			$log.log("error while fetching feedbacks");
		});
	};
	
	fetchFeedbacks();
});