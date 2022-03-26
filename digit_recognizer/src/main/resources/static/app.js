
let canvas;
let ctx; // context of canvas
let imageData;
let dragging = false;
let strokeColor = 'black';
let lineWidth = 20;

let canvasWidth = 400;
let canvasHeight = 400;

let xPoints = new Array();
let yPoints = new Array();

// Holds x & y position of mouse
class MouseLocation {
	constructor(x, y) {
		this.x = x,
			this.y = y;
	}
}

let loc = new MouseLocation(); // current location of the mouse

document.addEventListener('DOMContentLoaded', setupCanvas);

function setupCanvas() {
	canvas = document.getElementById('canvas');
	ctx = canvas.getContext('2d');
	ctx.strokeStyle = strokeColor;
	ctx.lineWidth = lineWidth;

	canvas.addEventListener("mousedown", ReactToMouseDown);
	canvas.addEventListener("mousemove", ReactToMouseMove);
	canvas.addEventListener("mouseup", ReactToMouseUp);
}

// get mouse position on canvas according to its position on page
function getMousePosition(x, y) {
	let canvasSizeData = canvas.getBoundingClientRect();
	return {
		x: (x - canvasSizeData.left) * (canvas.width / canvasSizeData.width),
		y: (y - canvasSizeData.top) * (canvas.height / canvasSizeData.height)
	};
}

// save current canvas state to array
function saveCanvas() {
	imageData = ctx.getImageData(0, 0, canvas.width, canvas.height);
}

// put imageData to canvas
function redrawCanvas() {
	ctx.putImageData(imageData, 0, 0);
}

function addPoint(x, y) {
	xPoints.push(x);
	yPoints.push(y);
}

function draw() {
	for (let i = 1; i < xPoints.length; i++) {
		ctx.beginPath();
		if (xPoints[i] != 0 && xPoints[i - 1] != 0) {
			ctx.fillRect(xPoints[i - 1] - 10, yPoints[i - 1] - 10, 20, 20);
		}
	}
}

function ReactToMouseDown(e) {
	canvas.style.cursor = "crosshair";
	loc = getMousePosition(e.clientX, e.clientY);
	saveCanvas();
	dragging = true;
	addPoint(loc.x, loc.y);
}

function ReactToMouseMove(e) {
	loc = getMousePosition(e.clientX, e.clientY);
	if (dragging) {
		if (loc.x > 0 && loc.x < canvasWidth && loc.y > 0 && loc.y < canvasHeight) {
			addPoint(loc.x, loc.y);
			redrawCanvas();
			draw();
		}
	}
}

function ReactToMouseUp(e) {
	canvas.style.cursor = "default";
	addPoint(0, 0);
	dragging = false;
}


function resetImage() {
	
	xPoints = new Array();
	yPoints = new Array();
	ctx.clearRect(0, 0, canvas.width, canvas.height);
	draw();
	
	let testCanvas = document.getElementById("resize-canvas");
	let testCtx = testCanvas.getContext('2d');
	testCtx.clearRect(0, 0, 28, 28)
	
	document.getElementById("fetch-text").innerHTML = "...";
}



async function sendImage() {

	let resizedCanvas = document.createElement('canvas');
	let resizedContext = resizedCanvas.getContext('2d');
	resizedCanvas.width = 28;
	resizedCanvas.height = 28;
	resizedContext.drawImage(canvas, 0, 0, 28, 28);
	let rawData = Array.prototype.slice.call(resizedContext.getImageData(0, 0, 28, 28).data);
	let dataToSend = rawData.filter(function(value, index, ar) {
		return index % 4 == 3
	});

	const res = await fetch("recognition/classify", {
		method: 'POST',
		mode: 'cors',
		cache: 'no-cache',
		credentials: 'omit',
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		},
		redirect: 'follow',
		referrerPolicy: 'no-referrer',
		body: JSON.stringify(dataToSend)
	});

	const txt = await res.text();
	document.getElementById("res-input").innerHTML = "Is " + txt + " your number?";
	let feedbackModal = new bootstrap.Modal(document.getElementById('feedbackModal'), {
		keyboard: false
	});
	feedbackModal.show();

}


function testImage() {
	let testCanvas = document.getElementById("resize-canvas");
	let testCtx = testCanvas.getContext('2d');

	testCtx.clearRect(0, 0, 28, 28);
	testCtx.drawImage(canvas, 0, 0, 28, 28);
}

async function sendResponse(isSuccess) {
	await fetch("statistics/update", {
		method: 'POST',
		mode: 'cors',
		cache: 'no-cache',
		credentials: 'same-origin',
		headers: {
		'Accept': 'application/json',
		'Content-Type': 'application/json'
		},
		redirect: 'follow',
		referrerPolicy: 'no-referrer',
		body: isSuccess
	});

	window.location.reload(true);
}

async function logout() {
	await fetch("/logout")
}