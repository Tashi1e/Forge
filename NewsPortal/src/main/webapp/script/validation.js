function checkValid() {
	checkBox = document.newsForm.checkBox;
	s = document.newsForm.checkBox[0].checked;
	for (var i = 0; i < checkBox.length; i++) {
		if (checkBox[i].checked == true) {
			return confirm(DELETE_CONFIRM_MESSAGE);
		}
	}
	alert(LIST_EMPTY_ERROR);
	return false;
}

function validateNewsForm() {

	// if (bCancel) { return true; }

	var message = "";

	var title = trim(document.forms[0].elements[2].value);
	var date = trim(document.forms[0].elements[3].value);
	var brief = trim(document.forms[0].elements[4].value);
	var content = trim(document.forms[0].elements[5].value);

	if (title == "") {
		message = message + TITLE_INCORRECT_MESSAGE + "\n";
	}

	if (DATE_PATTERN.length != date.length) {
		message = message + DATE_INCORRECT_MESSAGE + "\n";
	} else if (isValidDate(date, DATE_PATTERN) == false) {
		message = message + DATE_INCORRECT_MESSAGE + "\n";
	}

	if (brief == "") {
		message = message + BRIEF_INCORRECT_MESSAGE + "\n";
	}

	if (content == "") {
		message = message + CONTENT_INCORRECT_MESSAGE + "\n";
	}

	if (message == "") {
		return true;
	} else {
		alert(message);
	}
	return false;
}

function isValidDate(value, userFormat) {
	userFormat = userFormat || 'mm/dd/yyyy';
	var delimiter = /[^mdyMDY]/.exec(userFormat)[0];
	var theFormat = userFormat.split(delimiter);
	var theDate = value.split(delimiter);

	function isDate(date, format) {
		var m, d, y, i = 0, len = format.length, f;
		for (i; i < len; i++) {
			f = format[i];
			if (/m/.test(f)) {
				m = date[i];
			}
			if (/d/.test(f)) {
				d = date[i];
			}
			if (/y/.test(f)) {
				y = date[i];
			}
			if (/M/.test(f)) {
				m = date[i];
			}
			if (/D/.test(f)) {
				d = date[i];
			}
			if (/Y/.test(f)) {
				y = date[i];
			}
		}

		return (m > 0 && m < 13 && y && y.length === 4 && d > 0 && d <= (new Date(
				y, m, 0)).getDate());
	}

	return isDate(theDate, theFormat);
}

var bCancel = false;

function trim(s) {
	return s.replace(/^\s*/, "").replace(/\s*$/, "");
}
