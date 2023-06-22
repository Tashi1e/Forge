let text = document.querySelectorAll(".text");
let btn1 = document.getElementById('login');
let btn2 = document.getElementById('registration');
let btn3 = document.getElementById('signin');
let btn4 = document.getElementById('remember');
let lable = document.querySelectorAll(".ph-container label");
let check = document.getElementById('checkbox');
let command = document.getElementById('command')

btn1.onclick = function() {
	for (let txt of text) {
		txt.classList.add('loginForm');
		txt.addEventListener('transitionend', function() {
			btn1.type = 'hidden';
			btn3.type = 'submit';
			btn2.type = 'hidden';
			btn4.type = 'button';
			command.value = 'do_registration';
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
				btn2.type = 'submit';
				btn4.type = 'hidden';
				command.value = 'do_sign_in';
				for (let lbl of lable) {
					lbl.style.visibility = 'hidden';
				}
			});
		}
	}
});


//btn1.onclick = function() {
//	for (let txt of text) {
//		txt.classList.add('loginForm');
//		txt.addEventListener('transitionend', function(e) {
//			btn1.type = 'submit';
//			btn1.value = 'Sign-in';
//			btn1.classList.add('signIn');
//			btn2.type = 'button';
//			command.value = 'do_sign_in';
//			if (!check.checked) {
//				btn2.value = 'Remember';
//			}
//			else {
//				btn2.value = '\u{2713}';
//			}
//
//			btn2.classList.add('checkbox');
//			for (let lbl of lable) {
//				lbl.style.visibility = 'visible';
//			}
//		});
//	}
//}
//
//btn2.onclick = function() {
//	if (btn2.classList.contains("checkbox")) {
//		if (!check.checked) {
//			check.checked = true;
//			btn2.value = '\u{2713}';
//		} else {
//			check.checked = false;
//			btn2.value = 'Remember';
//		}
//	}
//}
//
//window.addEventListener('click', function(e) {
//	if (!box.contains(e.target) && !btn1.contains(e.target)) {
//		for (let lbl of lable) {
//			lbl.style.visibility = 'hidden';
//		}
//		for (let txt of text) {
//			txt.classList.remove('loginForm');
//			txt.addEventListener('transitionend', function(e) {
//				btn1.type = 'button';
//				btn1.value = 'Login';
//				btn1.classList.remove('signIn');
//				btn2.value = 'Sign-up';
//				btn2.classList.remove('checkbox');
//				btn2.type = 'submit';
//				command.value = 'go_to_registration_page';
//				for (let lbl of lable) {
//					lbl.style.visibility = 'hidden';
//				}
//			});
//		}
//	}
//});