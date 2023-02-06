const data = [
  { name: "Google", url: "https://www.google.com" },
  { name: "Bing", url: "https://www.bing.com" },
  { name: "Yahoo", url: "https://www.yahoo.com" },
  { name: "Ask Jeeves", url: "https://www.ask.com" },
];

const searchInput = document.getElementById("searchInput");
const searchResults = document.getElementById("searchResults");

searchInput.addEventListener("input", function (e) {
  const searchTerm = e.target.value;
  let results = "";

  for (let i = 0; i < data.length; i++) {
    if (data[i].name.toLowerCase().indexOf(searchTerm.toLowerCase()) !== -1) {
      results += `<a href="${data[i].url}">${data[i].name}</a><br>`;
    }
  }

  searchResults.innerHTML = results;
});
