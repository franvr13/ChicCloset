const stars = document.querySelectorAll('.star');
const ratingValue = document.getElementById('rating-value');

stars.forEach(star => {
  star.addEventListener('mouseover', () => {
    const value = star.getAttribute('data-value');
    stars.forEach(s => {
      if (s.getAttribute('data-value') <= value) {
        s.classList.add('hovered');
      } else {
        s.classList.remove('hovered');
      }
    });
  });

  star.addEventListener('mouseout', () => {
    stars.forEach(s => {
      s.classList.remove('hovered');
    });
  });

  star.addEventListener('click', () => {
    const value = star.getAttribute('data-value');
    ratingValue.textContent = value;
    stars.forEach(s => {
      if (s.getAttribute('data-value') <= value) {
        s.classList.add('selected');
      } else {
        s.classList.remove('selected');
      }
    });
  });
});