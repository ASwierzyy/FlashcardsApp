// js/api.js
const BASE_URL = '/api/entries';
const QUIZ_URL = '/api/quiz';

export async function fetchEntries(sortBy = 'id', order = '1') {
    const res = await fetch(`${BASE_URL}?sortBy=${sortBy}&order=${order}`);
    if (!res.ok) throw new Error('Could not fetch entries');
    return res.json();
}

export async function addEntry(polish, english, german) {
    const res = await fetch(BASE_URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ polish, english, german })
    });
    if (!res.ok) throw new Error('Could not add entry');
    return res.json(); // returns newly created Entry
}

export async function deleteEntry(id) {
    const res = await fetch(`${BASE_URL}/${id}`, {
        method: 'DELETE'
    });
    if (!res.ok) throw new Error('Could not delete entry');
    return true;
}

export async function searchEntries(type, value) {
    const res = await fetch(`${BASE_URL}/search?type=${type}&value=${value}`);
    if (!res.ok) throw new Error('Search failed');
    return res.json();
}

export async function fetchQuizQuestion() {
    const res = await fetch(`${QUIZ_URL}/new`);
    if (!res.ok) return null;
    return res.json();  // QuizQuestionDTO
}

export async function validateQuizAnswers(quizAnswer) {
    const res = await fetch(`${QUIZ_URL}/validate`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(quizAnswer)
    });
    if (!res.ok) throw new Error('Could not validate quiz');
    return res.json(); // QuizResultDTO
}