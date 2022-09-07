document.addEventListener("DOMContentLoaded", () => {
  console.log("js연결완료");
  const btn_stop = document.querySelector("div.btn_stop");
  const btn_start = document.querySelector("div.btn_start");
  const list_row = document.querySelector("div.list_row");

  if (btn_stop) {
    alert("stop 클립");
    btn_stop.addEventListener("click", () => {
      list_row.classList.add("stop-ani");
    });
  }
  if (btn_start) {
    btn_start.addEventListener("click", () => {
      list_row.classList.remove("stop-ani");
    });
  }
});
