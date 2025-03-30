import { fetchQuizQuestion, validateQuizAnswers } from './api.js';
import { renderQuizQuestion, showQuizResult } from './ui.js';

export async function startQuiz() {
    const question = await fetchQuizQuestion();
    renderQuizQuestion(question, 'quizArea');
    if (!question) return;

    document.getElementById('checkQuizBtn').addEventListener('click', checkQuiz);
}

async function checkQuiz() {
    const entryId = document.getElementById('quizEntryId').value;
    const sourceLang = document.getElementById('quizSourceLang').value;
    const answerLang1 = document.getElementById('answerLang1').value.trim();
    const answerLang2 = document.getElementById('answerLang2').value.trim();

    try {
        const result = await validateQuizAnswers({
            entryId: Number(entryId),
            sourceLang,
            answerLang1,
            answerLang2
        });
        showQuizResult(result);
    } catch (err) {
        alert('Error validating quiz: ' + err.message);
    }
}