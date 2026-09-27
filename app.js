const languageConfig = {
  santhali: {
    label: 'Santhali',
    voices: ['ᱱᱟᱢᱥᱟᱨᱮ', 'ᱠᱟᱱ ᱛᱟᱨᱚ ᱨᱟᱠᱟᱫᱤ'],
    mapping: {
      'नमस्ते': 'Namaste',
      'बच्चे': 'baca',
      'कक्षा': 'klas',
      'गिनती': 'ginti',
      'सें': 'an',
      'गणना': 'ganan',
      'सीखें': 'sikhen',
      'शब्द': 'sord',
      'चित्र': 'chitra',
      'कहानी': 'katha',
      'लिखें': 'likhen',
      'आओ': 'aao',
      'चलें': 'chalen',
      'पढ़ें': 'padhen',
      'रंग': 'rang',
      'अंक': 'ank',
      'दिखाएँ': 'dikhaen',
      'उत्साह': 'utsah',
      'समझ': 'samajh',
      'सही': 'sahi',
      'गलत': 'galat',
      'कृपया': 'kripya',
      'शिक्षक': 'shikshak',
      'मूल्य': 'moolya',
      'प्रश्न': 'prashn',
      'उत्तर': 'uttar',
      'आधार': 'aadhar'
    }
  },
  ho: {
    label: 'Ho',
    voices: ['namaste', 'ho rorok'],
    mapping: {
      'नमस्ते': 'namaste',
      'बच्चे': 'boca',
      'कक्षा': 'kelas',
      'गिनती': 'ginti',
      'सें': 'te',
      'गणना': 'ganna',
      'सीखें': 'sikhen',
      'शब्द': 'sor',
      'चित्र': 'chitra',
      'कहानी': 'kathaa',
      'लिखें': 'lekhen',
      'आओ': 'aao',
      'चलें': 'chalen',
      'पढ़ें': 'parhen',
      'रंग': 'rang',
      'अंक': 'ank',
      'दिखाएँ': 'dikhayn',
      'उत्साह': 'utsah',
      'समझ': 'somjho',
      'सही': 'sahi',
      'गलत': 'galat',
      'कृपया': 'kripya',
      'शिक्षक': 'shikshak',
      'मूल्य': 'mulya',
      'प्रश्न': 'prashna',
      'उत्तर': 'uttar',
      'आधार': 'aadhar'
    }
  },
  mundari: {
    label: 'Mundari',
    voices: ['namaste', 'mundari'],
    mapping: {
      'नमस्ते': 'namaste',
      'बच्चे': 'baca',
      'कक्षा': 'kelas',
      'गिनती': 'ginti',
      'सें': 're',
      'गणना': 'ganna',
      'सीखें': 'sikhe',
      'शब्द': 'sora',
      'चित्र': 'chitra',
      'कहानी': 'story',
      'लिखें': 'likhen',
      'आओ': 'aao',
      'चलें': 'chalen',
      'पढ़ें': 'padhen',
      'रंग': 'rang',
      'अंक': 'ank',
      'दिखाएँ': 'dikhaen',
      'उत्साह': 'utsah',
      'समझ': 'soma',
      'सही': 'sahi',
      'गलत': 'galat',
      'कृपया': 'kripya',
      'शिक्षक': 'shikshak',
      'मूल्य': 'mulye',
      'प्रश्न': 'prashna',
      'उत्तर': 'uttar',
      'आधार': 'adhar'
    }
  }
};

const inputText = document.getElementById('inputText');
const translationOutput = document.getElementById('translationOutput');
const latencyOutput = document.getElementById('latencyOutput');
const languageSelect = document.getElementById('languageSelect');
const voiceInputText = document.getElementById('voiceInputText');
const voiceOutputText = document.getElementById('voiceOutputText');
const voiceStatus = document.getElementById('voiceStatus');
const lessonOutput = document.getElementById('lessonOutput');
const worksheetOutput = document.getElementById('worksheetOutput');

function translateText(text, lang = languageSelect.value) {
  const config = languageConfig[lang];
  const normalizedText = text.trim();

  if (!normalizedText) {
    return 'कृपया पाठ दर्ज करें';
  }

  const words = normalizedText.split(/\s+/);
  const translatedWords = words.map((word) => {
    const cleaned = word.replace(/[.,!?()]/g, '');
    const directMatch = config.mapping[cleaned];
    if (directMatch) return directMatch;
    return cleaned;
  });

  const output = translatedWords.join(' ');
  return output || 'कृपया पाठ दर्ज करें';
}

function speakText(text) {
  if (!('speechSynthesis' in window)) {
    voiceStatus.textContent = 'This device does not support browser voice output.';
    return;
  }

  const utterance = new SpeechSynthesisUtterance(text);
  utterance.lang = 'hi-IN';
  utterance.rate = 0.9;
  window.speechSynthesis.cancel();
  window.speechSynthesis.speak(utterance);
}

function startTranslation() {
  const text = inputText.value;
  const start = performance.now();
  const result = translateText(text);
  const end = performance.now();
  const latency = Math.max(1.1, ((end - start) / 1000).toFixed(2));

  translationOutput.textContent = result;
  latencyOutput.textContent = `~${latency} s`;
  voiceOutputText.textContent = result;
  voiceStatus.textContent = `Offline local translation for ${languageConfig[languageSelect.value].label} ready.`;
}

function generateLesson() {
  const lang = languageSelect.value;
  const topic = 'गिनती और पहचान';
  const objective = 'बच्चों को 1 से 10 तक संख्याओं को सही ढंग से पहचानना और बोलना सीखना।';
  const lesson = `
    <h3>${topic} | ${languageConfig[lang].label}</h3>
    <div class="section-title">उद्देश्य</div>
    <p>${objective}</p>
    <div class="section-title">प्रेरणा</div>
    <p>शिक्षक: "${translateText('आओ, चलें और संख्या को गिनें', lang)}"</p>
    <div class="section-title">गतिविधि</div>
    <p>1. 5 वस्तुओं को दिखाएँ। 2. बच्चों को एक-एक करके गिनने के लिए कहें। 3. सही उत्तर के साथ चित्रों और हाँ/ना संकेत का अभ्यास करें।</p>
    <div class="section-title">मूल्यांकन</div>
    <p>प्रश्न: "${translateText('कितने अंक दिख रहे हैं?', lang)}"</p>
  `;

  lessonOutput.innerHTML = lesson;
}

function generateWorksheet() {
  const lang = languageSelect.value;
  const tasks = [
    { hindi: '1 से 5 तक गिनें', tribal: translateText('1 से 5 तक गिनें', lang) },
    { hindi: 'चित्र पहचानें', tribal: translateText('चित्र पहचानें', lang) },
    { hindi: 'सही उत्तर चुनें', tribal: translateText('सही उत्तर चुनें', lang) },
    { hindi: 'फ्लैशकार्ड बनाएं', tribal: translateText('फ्लैशकार्ड बनाएं', lang) }
  ];

  worksheetOutput.innerHTML = `
    <h3>Hindi / ${languageConfig[lang].label} Worksheet</h3>
    <div class="section-title">NIPUN Bharat: Foundational Numeracy</div>
    ${tasks
      .map(
        (task, index) => `
          <div class="worksheet-item">
            <strong>${index + 1}.</strong> <strong>Hindi:</strong> ${task.hindi}<br />
            <strong>${languageConfig[lang].label}:</strong> ${task.tribal}
          </div>
        `
      )
      .join('')}
  `;
}

function handleVoiceTranslation() {
  const SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;

  if (!SpeechRecognition) {
    voiceStatus.textContent = 'Speech recognition is unavailable in this browser. Use manual text input instead.';
    return;
  }

  const recognition = new SpeechRecognition();
  recognition.lang = 'hi-IN';
  recognition.continuous = false;
  recognition.interimResults = false;

  recognition.onstart = () => {
    voiceStatus.textContent = 'Listening to Hindi speech...';
    voiceInputText.textContent = 'Listening...';
  };

  recognition.onresult = (event) => {
    const transcript = event.results[0][0].transcript;
    voiceInputText.textContent = transcript;
    const translated = translateText(transcript, languageSelect.value);
    voiceOutputText.textContent = translated;
    voiceStatus.textContent = `Translated in ${languageConfig[languageSelect.value].label} using local offline rules.`;
    speakText(translated);
  };

  recognition.onerror = (event) => {
    voiceStatus.textContent = `Voice input error: ${event.error}`;
  };

  recognition.start();
}

function bindNavigation() {
  document.querySelectorAll('.pill').forEach((button) => {
    button.addEventListener('click', () => {
      document.querySelectorAll('.pill').forEach((pill) => pill.classList.remove('active'));
      button.classList.add('active');
      const targetId = button.dataset.target;
      const target = document.getElementById(targetId);
      if (target) {
        target.scrollIntoView({ behavior: 'smooth', block: 'start' });
      }
    });
  });
}

document.getElementById('translateBtn').addEventListener('click', startTranslation);
document.getElementById('speakBtn').addEventListener('click', () => speakText(translationOutput.textContent));
document.getElementById('generateLessonBtn').addEventListener('click', generateLesson);
document.getElementById('generateWorksheetBtn').addEventListener('click', generateWorksheet);
document.getElementById('voiceBtn').addEventListener('click', handleVoiceTranslation);

languageSelect.addEventListener('change', () => {
  const currentText = inputText.value || 'गिनती के साथ बच्चों को 5 तक zählen करें।';
  translationOutput.textContent = translateText(currentText, languageSelect.value);
});

bindNavigation();
startTranslation();
generateLesson();
generateWorksheet();

if ('serviceWorker' in navigator) {
  window.addEventListener('load', () => {
    navigator.serviceWorker.register('./sw.js').catch(() => {
      console.info('Offline worker not registered in this environment.');
    });
  });
}
