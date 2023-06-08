from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys 
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC 
import pyautogui
import time

driver = webdriver.Chrome()
driver.set_window_size(1200,700)
driver.get("https://www.wikipedia.org")
wait = WebDriverWait(driver, 300)

#1 - Проверить поиск 
search_field = wait.until(EC.element_to_be_clickable((By.ID, "searchInput")))   
search_field.send_keys("Зигмунд Фрейд")
search_field.send_keys(Keys.ENTER)



#2.1 - Нажать на посмотреть код и перейдя в новое окно проверить присутствует ли ссылка заглавной страницы
current_window = driver.current_window_handle
pyautogui.rightClick()
#time.sleep(5)
pyautogui.press("v")
for window_handle in driver.window_handles:
    if window_handle != current_window:
        driver.switch_to.window(window_handle)

#actions.key_down(Keys.CONTROL).send_keys("U").key_up(Keys.CONTROL).perform()       DID NOT WORK, alse deleted actionchains import and 

page_source = driver.page_source
if "Заглавная страница" in page_source:
    print("Ccылка для перехода в заглавную страницу присутствует")
else:
    print("Ccылка для перехода в заглавную страницу отсутствует")



driver.switch_to.window(current_window)                                   # обратно на главную страницу
wait.until(EC.presence_of_element_located((By.TAG_NAME, "body")))



#2.2 - Проверить наличие рабочей ссылки для перехода в заглавную страницу
nazad = wait.until(EC.element_to_be_clickable((By.XPATH, "//*[ text() = 'Заглавная страница']")))
if nazad:
    print("Ccылка для перехода в заглавную страницу рабочая!")
else:
    print("Ccылка для перехода в заглавную страницу не рабочая!") 
nazad.click()
wait.until(EC.presence_of_element_located((By.TAG_NAME, "body")))   


search_results = driver.find_elements(By.CSS_SELECTOR, "div.main-box-responsive-image")   # Перейти по ссылке первой статьи
search_results[0].find_element(By.CSS_SELECTOR, "a").click()



#3 - Проверить состоит ли статья из параграфов 
article_paragraphs = wait.until(EC.presence_of_all_elements_located((By.TAG_NAME, "p")))
if len(article_paragraphs) > 0:
    print("Состоит из параграфов")
else:
    print("Не содержит параграфов")

    

#4 - Проверить содержит ли статья содержание
soderzhanye = wait.until(EC.presence_of_element_located((By.XPATH, "//*[ text() = 'Содержание']")))
if soderzhanye:
    print("Статья содержит содержание")
else:
    print("Статья без содержания")


#5 - Поменять язык (на англ)
change_lang = wait.until(EC.presence_of_element_located((By.LINK_TEXT, "English"))).click()
wait.until(EC.presence_of_element_located((By.TAG_NAME, "body")))

url = driver.current_url
if "en" in url:
    print("Язык успешно изменен")
else:
    print("Не удалось изменить язык")


# Выйти
driver.quit()
