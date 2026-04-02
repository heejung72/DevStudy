// devStudy - Main JavaScript

document.addEventListener('DOMContentLoaded', function () {

    // ===== 코드 하이라이팅 =====
    document.querySelectorAll('pre code').forEach((block) => {
        hljs.highlightElement(block);
    });

    // ===== 퀴즈: 모든 문제에 답했을 때 제출 버튼 활성화 =====
    const submitBtn = document.getElementById('submitBtn');
    if (submitBtn) {
        const questionCards = document.querySelectorAll('.question-card');
        const totalQuestions = questionCards.length;

        function checkAllAnswered() {
            let answered = 0;
            questionCards.forEach((card) => {
                const radios = card.querySelectorAll('input[type="radio"]');
                const isAnswered = Array.from(radios).some(r => r.checked);
                if (isAnswered) answered++;
            });
            submitBtn.disabled = (answered < totalQuestions);
            if (answered === totalQuestions) {
                submitBtn.classList.remove('btn-warning');
                submitBtn.classList.add('btn-success');
                submitBtn.innerHTML = '<i class="bi bi-check2-all me-2"></i>모든 답안 완료! 제출하기';
            }
        }

        document.querySelectorAll('.option-radio').forEach((radio) => {
            radio.addEventListener('change', checkAllAnswered);
        });
    }

    // ===== 코드 복사 =====
    window.copyCode = function(btn) {
        const pre = btn.closest('.card').querySelector('pre code');
        if (pre) {
            navigator.clipboard.writeText(pre.innerText).then(() => {
                const original = btn.innerHTML;
                btn.innerHTML = '<i class="bi bi-check me-1"></i>복사됨!';
                btn.classList.add('btn-success');
                btn.classList.remove('btn-outline-light');
                setTimeout(() => {
                    btn.innerHTML = original;
                    btn.classList.remove('btn-success');
                    btn.classList.add('btn-outline-light');
                }, 2000);
            });
        }
    };

    // ===== 퀴즈 옵션 라벨 클릭 시 선택 효과 =====
    document.querySelectorAll('.option-label').forEach((label) => {
        label.addEventListener('click', function () {
            const name = this.querySelector('input[type="radio"]')?.name;
            if (name) {
                document.querySelectorAll(`input[name="${name}"]`).forEach((radio) => {
                    radio.closest('.option-label')?.classList.remove('selected');
                });
                this.classList.add('selected');
            }
        });
    });

    // ===== 스크롤 시 navbar 그림자 =====
    window.addEventListener('scroll', function () {
        const navbar = document.querySelector('.navbar');
        if (navbar) {
            if (window.scrollY > 10) {
                navbar.style.boxShadow = '0 2px 20px rgba(0,0,0,0.3)';
            } else {
                navbar.style.boxShadow = 'none';
            }
        }
    });

    // ===== 현재 nav 링크 활성화 =====
    const currentPath = window.location.pathname;
    document.querySelectorAll('.nav-link').forEach((link) => {
        if (link.getAttribute('href') === currentPath) {
            link.classList.add('active');
        }
    });
});
