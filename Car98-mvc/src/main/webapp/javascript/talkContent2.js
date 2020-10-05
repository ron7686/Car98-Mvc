function doFirst() {
  document.getElementById("btnImage").onchange = fileChange;
}
function fileChange() {
  let file = document.getElementById("btnImage").files[0];
  let readFile = new FileReader();
  readFile.readAsDataURL(file);
  readFile.addEventListener("load", function (e) {
    let image = document.getElementById("image");
    image.src = this.result;
    image.style.maxWidth = "260px";
    image.style.maxHeight = "400px";
    
  });
  
  
  
}
window.addEventListener("load", doFirst);


