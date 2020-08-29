package com.example.myapplication.checkout.paymentfragment;

public class Payment_request {
    private String amount;

    private String webhook;

    private String purpose;

    private String shorturl;

    private String buyer_name;

    private String send_email;

    private String created_at;

    private String email_status;

    private String allow_repeated_payments;

    private String expires_at;

    private String phone;

    private String longurl;

    private String id;

    private String customer_id;

    private String modified_at;

    private String send_sms;

    private String email;

    private String sms_status;

    private String redirect_url;

    private String status;

    public String getAmount ()
    {
        return amount;
    }

    public void setAmount (String amount)
    {
        this.amount = amount;
    }

    public String getWebhook ()
    {
        return webhook;
    }

    public void setWebhook (String webhook)
    {
        this.webhook = webhook;
    }

    public String getPurpose ()
    {
        return purpose;
    }

    public void setPurpose (String purpose)
    {
        this.purpose = purpose;
    }

    public String getShorturl ()
    {
        return shorturl;
    }

    public void setShorturl (String shorturl)
    {
        this.shorturl = shorturl;
    }

    public String getBuyer_name ()
    {
        return buyer_name;
    }

    public void setBuyer_name (String buyer_name)
    {
        this.buyer_name = buyer_name;
    }

    public String getSend_email ()
    {
        return send_email;
    }

    public void setSend_email (String send_email)
    {
        this.send_email = send_email;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public String getEmail_status ()
    {
        return email_status;
    }

    public void setEmail_status (String email_status)
    {
        this.email_status = email_status;
    }

    public String getAllow_repeated_payments ()
    {
        return allow_repeated_payments;
    }

    public void setAllow_repeated_payments (String allow_repeated_payments)
    {
        this.allow_repeated_payments = allow_repeated_payments;
    }

    public String getExpires_at ()
    {
        return expires_at;
    }

    public void setExpires_at (String expires_at)
    {
        this.expires_at = expires_at;
    }

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone (String phone)
    {
        this.phone = phone;
    }

    public String getLongurl ()
    {
        return longurl;
    }

    public void setLongurl (String longurl)
    {
        this.longurl = longurl;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getCustomer_id ()
    {
        return customer_id;
    }

    public void setCustomer_id (String customer_id)
    {
        this.customer_id = customer_id;
    }

    public String getModified_at ()
    {
        return modified_at;
    }

    public void setModified_at (String modified_at)
    {
        this.modified_at = modified_at;
    }

    public String getSend_sms ()
    {
        return send_sms;
    }

    public void setSend_sms (String send_sms)
    {
        this.send_sms = send_sms;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getSms_status ()
    {
        return sms_status;
    }

    public void setSms_status (String sms_status)
    {
        this.sms_status = sms_status;
    }

    public String getRedirect_url ()
    {
        return redirect_url;
    }

    public void setRedirect_url (String redirect_url)
    {
        this.redirect_url = redirect_url;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [amount = "+amount+", webhook = "+webhook+", purpose = "+purpose+", shorturl = "+shorturl+", buyer_name = "+buyer_name+", send_email = "+send_email+", created_at = "+created_at+", email_status = "+email_status+", allow_repeated_payments = "+allow_repeated_payments+", expires_at = "+expires_at+", phone = "+phone+", longurl = "+longurl+", id = "+id+", customer_id = "+customer_id+", modified_at = "+modified_at+", send_sms = "+send_sms+", email = "+email+", sms_status = "+sms_status+", redirect_url = "+redirect_url+", status = "+status+"]";
    }
}
