function checkFormOnSubmit() {
	try {
		// 每个业务表单，都可以有一个checkForm函数，用于在提交表单之前对表单进行一些额外的处理。
		// 以及校验表单是否正确等操作。
		return checkForm();
	} catch (ex) {
		return true;
	}
}
var setDateTimePicker = function() {
	$('input[name$="Time"]').each(function() {
		$(this).attr("readonly", true);
	});
	// 绑定所有Time结尾的输入框，让它们能够选择日期时间
//	$('input[name$="Time"]').datetimepicker({
//		locale : 'zh-CN',
//		// format: "L"
//		format : "YYYY-MM-DD HH:mm",
//		defaultDate : new Date(),
//		showClose: true,
//		keepOpen: true,
//		debug: true
//	});
	$('input[name$="Time"]').datetimepicker({
		language : 'zh-CN',
		// format: "L"
		format : "yyyy-mm-dd hh:ii"
	});
};

var setDatePicker = function() {
	// ~= 表示包含
	// $= 以X值结尾
	// ^= 以X值开头
	$('input[name$="Date"]').each(function() {
		$(this).attr("readonly", true);
	});
	// 绑定所有Date结尾的输入框，让它们能够选择日期
//	$('input[name$="Date"]').datetimepicker({
//		locale : 'zh-cn',
//		// format: "L"
//		format : "YYYY-MM-DD",
//		defaultDate : new Date()
//	});
	$('input[name$="Date"]').datetimepicker({
		language : 'zh-CN',
		// format: "L"
		format : "yyyy-mm-dd"
	});
};

/**
 * 设置输入框的name属性的值
 */
function setInputName() {
	// 默认table里面所有行里面，都带了一个0，要在提交之前，把0替换成 0～行数减一的数字
	var rows = $(".data-list tbody tr");
	// console.debug(rows);
	// return false;
	try {
		for (var index = 0; index < rows.length; index++) {
			// 找到一行里面，所有输入name属性带中括号的控件的name属性的值
			var tr = rows[index];
			// alert($(tr).html());

			var inputs = $("input", tr);
			var textareas = $("textarea", tr);
			var selects = $("select", tr);
			inputs.push(textareas);
			inputs.push(selects);
			// alert(inputs.length);
			for (var i = 0; i < inputs.length; i++) {
				var input = inputs[i];
				// alert($(this).attr("name"));
				var name = $(input).attr("name");
				if (/\[\d+\]/.test(name)) {
					// 把name属性的值里面的中括号里面的数字替换掉
					name = name.replace(/\[\d+\]/, "[" + index + "]");
					// alert(name);
					$(input, tr).attr("name", name);
				}
			}
		}
		return true;
	} catch (e) {
		alert(e);
	}

	return false;
}