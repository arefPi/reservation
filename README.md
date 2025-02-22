مستند فنی سیستم رزرو پیشرفته

۱. مقدمه
این مستند توضیحاتی در مورد طراحی، پیاده‌سازی و بهینه‌سازی‌های انجام‌شده در سیستم رزرو ارائه می‌دهد. هدف این سیستم ارائه یک روش کارآمد برای رزرو زمان‌های خالی با پشتیبانی از بار همزمان بالا است.

۲. معماری سیستم
۲.۱ معماری مورد استفاده
این سیستم از معماری N-Tier استفاده می‌کند که شامل لایه‌های مختلف برای مدیریت داده، منطق کسب‌وکار و ارائه‌ی سرویس به کاربران است. این معماری باعث جداسازی مسئولیت‌ها و بهبود قابلیت نگهداری سیستم می‌شود.

۲.۲ تکنولوژی‌های مورد استفاده Backend: Spring Boot (جاوا)
پایگاه داده: PostgreSQL برای ذخیره‌سازی داده‌ها
مدیریت همزمانی: استفاده از Redisson برای پیاده‌سازی قفل توزیع‌شده و جلوگیری از رزرو همزمان یک زمان خاص توسط چند کاربر
مستندسازی API: استفاده از Swagger

۲.۳ کش کردن اطلاعات
در این پروژه به دلیل اینکه نمایش تمامی زمان‌های فعال در سیستم پیاده‌سازی نشده بود صرفاً اطلاعات کاربران (User Data) در Redis کش شده است.


۳. مدیریت همزمانی و قفل‌گذاری توزیع‌شده
برای جلوگیری از رزرو همزمان یک زمان مشخص توسط چند کاربر، از قفل‌گذاری توزیع‌شده با Redisson و Redis استفاده شده است.

۵.۱ نحوه‌ی پیاده‌سازی قفل توزیع‌شده
هر زمان خالی دارای یک کلید قفل منحصربه‌فرد در Redis است.
هنگام رزرو، کلید قفل ایجاد شده و تا پایان تراکنش، سایر درخواست‌ها امکان دسترسی به آن را ندارند.
درصورتی‌که کاربر نتواند در زمان معین قفل را بگیرد، درخواست وی رد می‌شود.
پیاده سازی به شکلی انجام شده که بدون تغییر در لایه سرویس میتوان روش دیگری همانند Actor Model را برای مدیریت همزمانی استفاده کرد.

۴. پیشنهاد برای بهبود سیستم
۴.۱ تضمین در دسترس بودن سیستم و بهبود عملکرد
برای بهبود مقیاس‌پذیری و در دسترس بودن سیستم، می‌توان از معماری Event-Driven و CQRS استفاده کرد. این کار باعث تفکیک عملیات خواندن و نوشتن و کاهش بار روی پایگاه داده می‌شود.

۴.۲ معماری پیشنهادی
در این معماری، سیستم به چهار ماژول مستقل تقسیم می‌شود:

ماژول reservation-api:
پردازش درخواست‌های REST API
ارسال درخواست‌ها به Message Broker (مثل Kafka یا RabbitMQ)

ماژول reservation-core:
دریافت درخواست‌ها از Message Broker
پردازش رزروها و ارسال نتیجه به Message Broker

ماژول reservation-write-store:(CQRS Projector)
خواندن پیام‌ها از Message Broker
ذخیره‌ی اطلاعات رزرو در پایگاه داده (PostgreSQL)

ماژول reservation-read-store:
مرجع خواندن اطلاعات رزروها

ذخیره‌ی داده‌های خواندنی در یک پایگاه داده بهینه‌شده برای خواندن (مثل Elasticsearch یا Redis)

۴.۳ مزایای این معماری
✅ بهبود مقیاس‌پذیری: کاهش بار روی پایگاه داده‌ی اصلی
✅ جداسازی وظایف: افزایش خوانایی و نگهداری آسان‌تر کد
✅ بهینه‌سازی پردازش رزروها: جلوگیری از تداخل عملیات خواندن و نوشتن

۶.۴ چالش‌ها و پیچیدگی‌های پیاده‌سازی
🚧 مدیریت تراکنش‌ها: نیاز به مدیریت تراکنش‌های توزیع‌شده بین ماژول‌ها
🚧 مدیریت پیام‌ها: اطمینان از عدم از بین رفتن پیام‌ها در Message Broker
🚧 نیاز به منابع بیشتر: افزایش هزینه‌های سرور و نگهداری سیستم









