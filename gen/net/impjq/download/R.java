/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * aapt tool from the resource data it found.  It
 * should not be modified by hand.
 */

package net.impjq.download;

public final class R {
    public static final class attr {
    }
    public static final class drawable {
        public static final int icon=0x7f020000;
    }
    public static final class id {
        public static final int appIcon=0x7f050003;
        public static final int description=0x7f050006;
        public static final int download_progress_bar=0x7f050002;
        public static final int progress_bar=0x7f050007;
        public static final int progress_text=0x7f050004;
        public static final int start_download_button=0x7f050001;
        public static final int title=0x7f050005;
        public static final int url_input_edittext=0x7f050000;
    }
    public static final class layout {
        public static final int main=0x7f030000;
        public static final int status_bar_ongoing_event_progress_bar=0x7f030001;
    }
    public static final class string {
        /**  This is the name of the Download Manager application. 
         */
        public static final int app_label=0x7f040002;
        public static final int app_name=0x7f040001;
        /**  This is the title that is used when displaying the notification
    for a download that doesn't have a title associated with it. 
         */
        public static final int download_unknown_title=0x7f04000b;
        public static final int hello=0x7f040000;
        /**  When a download completes, a notification is displayed, and this
        string is used to indicate that the download successfully completed.
        Note that such a download could have been initiated by a variety of
        applications, including (but not limited to) the browser, an email
        application, a content marketplace. 
         */
        public static final int notification_download_complete=0x7f04000e;
        /**  When a download completes, a notification is displayed, and this
        string is used to indicate that the download failed.
        Note that such a download could have been initiated by a variety of
        applications, including (but not limited to) the browser, an email
        application, a content marketplace. 
         */
        public static final int notification_download_failed=0x7f04000f;
        /**  When there are three or more simultaneous outstanding downloads from a
        single application, they are displayed as a single notification,
        and the expanded notification view uses this string to indicate
        downloads beyond the first two, i.e. "[title], [title] and [n] more".
        This is the " and [n] more" part, including the leading space, and it's
        used regardless of the number of additional downloads. 
         */
        public static final int notification_filename_extras=0x7f04000d;
        /**  When there are multiple simultaneous outstanding downloads from a
        single application, they are displayed as a single notification,
        and the expanded notification view displays the first two download
        names separated with this string, i.e. "[title], [title]"
        or "[title], [title] and [n] more". This is the comma + space
        that separates the first two titles, and it's used both when there
        are exactly two and more than two titles. 
         */
        public static final int notification_filename_separator=0x7f04000c;
        /**  This is the long description of a permission associated with the
        Android Download Manager. It is displayed as part of the description
        of any application that was granted that permission.
        This specific permission allows an application to tell other
        applications that their downloads have completed. 
         */
        public static final int permdesc_downloadCompletedIntent=0x7f040008;
        /**  This is the long description of a permission associated with the
        Android Download Manager. It is displayed as part of the description
        of any application that was granted that permission.
        This specific permission controls access to the Download Manager by
        applications that initiate downloads. 
         */
        public static final int permdesc_downloadManager=0x7f040004;
        /**  This is the long description of a permission associated with the
        Android Download Manager. It is displayed as part of the description
        of any application that was granted that permission.
        This specific permission controls access to some advanced (and
        dangerous) features from the Download Manager that are needed by
        system applications but aren't necessary for regular applications
        that just initiate plain downloads. 
         */
        public static final int permdesc_downloadManagerAdvanced=0x7f040006;
        /**  Description for the permission to see all downloads to EXTERNAL 
         */
        public static final int permdesc_seeAllExternal=0x7f04000a;
        public static final int permlab_downloadCompletedIntent=0x7f040007;
        /**  This is the short description of a permission associated with the
        Android Download Manager. It is displayed as part of the description
        of any application that was granted that permission.
        This specific permission controls access to the Download Manager by
        applications that initiate downloads. 
         */
        public static final int permlab_downloadManager=0x7f040003;
        /**  This is the short description of a permission associated with the
        Android Download Manager. It is displayed as part of the description
        of any application that was granted that permission.
        This specific permission controls access to some advanced (and
        dangerous) features from the Download Manager that are needed by
        system applications but aren't necessary for regular applications
        that just initiate plain downloads. 
         */
        public static final int permlab_downloadManagerAdvanced=0x7f040005;
        /**  Title for permission to see all downloads to EXTERNAL 
         */
        public static final int permlab_seeAllExternal=0x7f040009;
    }
}
