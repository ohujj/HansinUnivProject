// 필수 필드에 대한 간단한 폼 검증
document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector('form');

    form.addEventListener('submit', function(event) {
        const emailField = document.getElementById('email');
        const passwordField = document.getElementById('password');

        if (!emailField.value || !passwordField.value) {
            alert('이메일과 비밀번호는 필수 입력 사항입니다!');
            event.preventDefault(); // 폼 제출 중단
        }
    });
});

// 기본적인 상호작용 처리 (예: 포커스 시 입력 필드 강조)
const inputs = document.querySelectorAll('input');
inputs.forEach(input => {
    input.addEventListener('focus', function() {
        input.style.borderColor = 'blue'; // 포커스 시 테두리 색 변경
    });
    input.addEventListener('blur', function() {
        input.style.borderColor = ''; // 포커스 해제 시 테두리 원상 복귀
    });
});
