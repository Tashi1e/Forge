let text = document.querySelectorAll(".text");
let btn1 = document.getElementById('login');
let btn2 = document.getElementById('registration');
let btn3 = document.getElementById('signin');
let btn4 = document.getElementById('remember');
let lable = document.querySelectorAll(".logFormLabels");
let check = document.getElementById('checkbox');

btn1.onclick = function() {
	for (let txt of text) {
		txt.classList.add('loginForm');
		txt.addEventListener('transitionend', function() {
			btn1.type = 'hidden';
			btn3.type = 'submit';
			btn2.type = 'hidden';
			btn4.type = 'button';
			if (!check.checked) {
				btn4.value = '\u{2717} '+check.value;
			}
			else {
				btn4.value = '\u{2713} '+check.value;
			}

			btn2.classList.add('checkbox');
			for (let lbl of lable) {
				lbl.style.visibility = 'visible';
			}
		});
	}
}

btn4.onclick = function() { 
	if (!check.checked) {
		check.checked = true;
		btn4.value = '\u{2713} '+check.value;
	} else {
		check.checked = false;
		btn4.value = '\u{2717} '+check.value;
	}
}

window.addEventListener('click', function(e) {
	if (!box.contains(e.target) && !btn1.contains(e.target)) {
		for (let lbl of lable) {
			lbl.style.visibility = 'hidden';
		}
		for (let txt of text) {
			txt.classList.remove('loginForm');
			txt.addEventListener('transitionend', function() {
				btn1.type = 'button';
				btn3.type = 'hidden';
				btn2.type = 'button';
				btn4.type = 'hidden';
				for (let lbl of lable) {
					lbl.style.visibility = 'hidden';
				}
			});
		}
	}
});


//================ Registration form =================

// Get the modal
var modal = document.getElementById("regModal");

// Get the button that opens the modal
var btn = document.getElementById("registration");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
btn.onclick = function() {
  modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
//window.onclick = function(event) {
//  if (event.target == modal) {
//    modal.style.display = "none";
//  }
//}