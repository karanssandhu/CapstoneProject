function getCapstone(id) { 
	if (document.getElementById("capstone" + id).innerHTML == "") { 
		fetch('http://localhost:8080/getCapstone/' + id)  // use HomeControllerto fetch from our service.
		//fetch('http://localhost:8080/capstones/' + id)
		.then(capstone => capstone.json()) // JSONify the data returned.
		.then(function(capstone) { // with the JSON data
		
			// modify textToDisplay below here!
			var textToDisplay=""; // create and append to a blank var
			textToDisplay+= "ID: " + capstone.id + "<br>";
			textToDisplay+= "Title: " + capstone.title + "<br>";
			textToDisplay+= "Link: " + "<a href="+capstone.link+">"+ capstone.link+"</a>"+"<br>";
			textToDisplay+= "Team Name: " + capstone.teamName+ "<br>";
			textToDisplay+= "Total Up Votes!: " + capstone.rank+ "<br>";
			
			// finally, change our relevant div to display the var
			document.getElementById("capstone"+id).innerHTML=textToDisplay;
			});
			
			
		}else { 
			document.getElementById("capstone" + id).innerHTML = ""; 
			} 
	}
	
	
	function verify() {
	var password1 = document.forms['form']['password'].value;
	var password2 = document.forms['form']['verifyPassword'].value;
	if (password1 == null || password1 == "" || password1 != password2) {
			document.getElementById("errors").innerHTML= "Please check your passwords";
			return false;
		} 
	}