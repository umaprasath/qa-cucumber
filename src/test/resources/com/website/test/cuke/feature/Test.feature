@Test1
Feature: Test1
Scenario: UJ37 - Verify free text search result in Home page
	
	Given I go to Carers "Home" page
	When User enters first three character 
	| Field 			| Value |
	| Search text 		|res	|
	Then should display list of suggested terms
	When User enters search text character 
	| Field 			| Value         |
	| Search text 		|respite care	|
	Then should display featured article as results
	| Field 			| Value |
	| Search result 	|Respite|
	Then verify display of Show links to Find a service and Guided search on the search result
	