const canvas = document.getElementById("game");
const ctx = canvas.getContext("2d");

// set canvas size
canvas.width = 200;
canvas.height = 400;

// define block size and number of rows and columns
const blockSize = 20;
const rows = canvas.height / blockSize;
const columns = canvas.width / blockSize;

// define an array of tetrominos
const tetrominos = [
  [
    [0, 1, 0],
    [1, 1, 1],
    [0, 0, 0],
  ],
  [
    [2, 2, 0],
    [0, 2, 2],
    [0, 0, 0],
  ],
  [
    [0, 3, 3],
    [3, 3, 0],
    [0, 0, 0],
  ],
  [
    [4, 0, 0],
    [4, 4, 4],
    [0, 0, 0],
  ],
  [
    [0, 0, 5],
    [5, 5, 5],
    [0, 0, 0],
  ],
  [
    [6, 6, 6, 6],
    [0, 0, 0, 0],
    [0, 0, 0, 0],
    [0, 0, 0, 0],
  ],
  [
    [7, 7],
    [7, 7],
  ],
];

// choose a random tetromino and its color
const randomTetromino =
  tetrominos[Math.floor(Math.random() * tetrominos.length)];
const color = "blue";

// draw a tetromino on the canvas
function draw() {
  for (let r = 0; r < randomTetromino.length; r++) {
    for (let c = 0; c < randomTetromino[r].length; c++) {
      if (randomTetromino[r][c] > 0) {
        ctx.fillStyle = color;
        ctx.fillRect(c * blockSize, r * blockSize, blockSize, blockSize);
      }
    }
  }
}

draw();
