window.addEventListener('load', async function () {
    await new Promise(r => setTimeout(r, 500));
    // document.getElementById('preloader').style.display = 'none';
    $("#preloader").fadeOut(400);
});