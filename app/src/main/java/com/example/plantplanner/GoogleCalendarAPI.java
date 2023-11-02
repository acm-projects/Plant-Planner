package com.example.plantplanner;

public class GoogleCalendarAPI {


    //allows us to check what stage of the requirements the user is in
    public final class Constants {
        public static final int REQUEST_ACCOUNT_PICKER = 1000;
        public static final int REQUEST_AUTHORIZATION = 1001;
        public static final int REQUEST_GOOGLE_PLAY_SERVICES = 1002;
        public static final int REQUEST_PERMISSION_GET_ACCOUNTS = 1003;
        final String PREF_ACCOUNT_NAME = "getCalendarEvent";
    }

    //model of data from calendar(title, date, description, frequency???)
    public class GetEventModel() {
        private int id = 0;
        private String summary = "";
        private String startDate = "";
        private String description = "";

        public GetEventModel() {
        }
        public GetEventModel(int id, String summary, String startDate, String description) {
            this.id = id;
            this.summary = summary;
            this.startDate = startDate;
            this.description = description;
        }

        // Getters
        public int getId() { return id; }
        public String getSummary() { return summary; }
        public String getStartDate() { return startDate; }
        public String getDescription() { return description; }

        // Setters
        public void setId(int id) { this.id = id; }
        public void setSummary(String summary) { this.summary = summary; }
        public void setStartDate(String startDate) { this.startDate = startDate; }
        public void setDescription(String description) { this.description = description; }

        // Equals and HashCode (Override as needed)
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            GetEventModel that = (GetEventModel) obj;
            return id == that.id &&
                    objects.equals(summary, that.summary) &&
                    bjects.equals(startDate, that.startDate) &&
                    objects.equals(description, that.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, summary, startDate, description);
        }

        // ToString (Override as needed)

        @Override
        public String toString() {
            return "GetEventModel{" +
                    "id=" + id +
                    ", summary='" + summary + '\'' +
                    ", startDate='" + startDate + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }

    //To access goggle account
    private var mCredential: GoogleAccountCredential? = null

    //to access calendar
    private var mService: Calendar? = null

    //check if there is an account(mCredential) then pass parameter to get the calendar
    private fun initCredentials() {
        mCredential = GoogleAccountCredential.usingOAuth2(
                        requireContext(),
                        arrayListOf(CalendarScopes.CALENDAR)
                )
                .setBackOff(ExponentialBackOff())
        initCalendarBuild(mCredential)
    }

    //define mService variable,
    private fun initCalendarBuild(credential: GoogleAccountCredential?) {
        val transport = AndroidHttp.newCompatibleTransport()
        val jsonFactory = JacksonFactory.getDefaultInstance()
        mService = Calendar.Builder(
                        transport, jsonFactory, credential
                )
                .plantplanner("GetEventCalendar")
                .build()
    }

    //perform checks then use api to call the calendar
    private fun getResultsFromApi() {
        if (!isGooglePlayServicesAvailable()) {
            acquireGooglePlayServices()
        }
        else if (mCredential!!.selectedAccountName == null) {
            chooseAccount()
        }
        else if (!isDeviceOnline()) {
            binding.txtOut.text = "No network connection available."
        }
        else {
            makeRequestTask()
        }
    }

    // first if in getResultsFromApi()
    private fun acquireGooglePlayServices() {
        val apiAvailability = GoogleApiAvailability.getInstance()
        val connectionStatusCode = apiAvailability.isGooglePlayServicesAvailable(requireContext())
        if (apiAvailability.isUserResolvableError(connectionStatusCode)) {
            showGooglePlayServicesAvailabilityErrorDialog(connectionStatusCode)
        }
    }

    // first else if in getResultsFromApi(), prompts user to log in if there is no account found
    private fun chooseAccount() {
        if (EasyPermissions.hasPermissions(
                requireContext(), Manifest.permission.GET_ACCOUNTS
        )
        ) {
            val accountName = this.activity?.getPreferences(Context.MODE_PRIVATE)
                    ?.getString(PREF_ACCOUNT_NAME, null)
            if (accountName != null) {
                mCredential!!.selectedAccountName = accountName
                getResultsFromApi()
            } else {
                // Start a dialog from which the user can choose an account
                startActivityForResult(
                        mCredential!!.newChooseAccountIntent(),
                        REQUEST_ACCOUNT_PICKER
                )
            }
        } else {
            // Request the GET_ACCOUNTS permission via a user dialog
            EasyPermissions.requestPermissions(
                    this,
                    "This app needs to access your Google account (via Contacts).",
                    REQUEST_PERMISSION_GET_ACCOUNTS,
                    Manifest.permission.GET_ACCOUNTS
            )
        }
    }

    fun <R> CoroutineScope.executeAsyncTask(
            onStart: () -> Unit,
            doInBackground: () -> R,
            onPostExecute: (R) -> Unit,
            onCancelled: () -> Unit) = launch
    {
        onStart()
        val result = withContext(Dispatchers.IO) {
            doInBackground() // runs in background thread without blocking the Main Thread
        }
        onPostExecute(result) // runs in Main Thread
        onCancelled()
    }


}
