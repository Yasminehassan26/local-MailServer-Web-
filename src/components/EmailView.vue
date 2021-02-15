<template>
  <v-container>
    <v-dialog v-model="show" persistent width="800px">
      <v-card class="pa-3">
        <v-col cols="12">
          <v-text-field
              v-model="email.sender"
              label="From"
              readonly
          ></v-text-field>
        </v-col>
        <v-col cols="12">
          <v-combobox v-model="email.receiver"
                      :readonly="!isDraft"
                      chips
                      class="tag-input"
                      deletable-chips
                      label="To"
                      multiple
          >
          </v-combobox>
        </v-col>
        <v-row>
          <v-col cols="9">
            <v-text-field
                v-model="email.subject"
                :readonly="!isDraft"
                class="pa-3"
                label="Subject"
            ></v-text-field>
          </v-col>
          <v-col
              v-show="isDraft" cols="3">
            <v-select
                v-model="email.importance"
                :items="['1','2','3','4']"
                dense
                :value="email.importance"
                label="Importance"
                outlined
            ></v-select>
          </v-col>
          <v-col v-show="!isDraft" cols="3">
            <v-text-field
                v-model="email.importance "
                class="pa-3"
                label="Priority"
                readonly
            ></v-text-field>
          </v-col>
        </v-row>

        <v-divider></v-divider>
        <v-col cols="12">
          <v-textarea v-model="email.emailBody"
                      :readonly="!isDraft"
                      class="pa-3"
                      label="Body"></v-textarea>
        </v-col>
        <v-card-actions>
          <v-list>
            <v-list-item-group v-model="attachIndex">
              <v-list-item
                  v-for="(item,i) in email.attachments"
                  :key="i"
                  :value="i"
                  @click="Download(item)">
                <v-list-item-content>
                  <v-list-item-title v-text="item"></v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list-item-group>
          </v-list>
          <v-spacer></v-spacer>
          <v-btn
              v-show="isDraft"
              color="primary"
              text
              @click.stop="sendEmail"
          >
            Send
          </v-btn>
          <v-btn
              color="primary"
              text
              @click.stop="close"
          >
            Close
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <template>
      <alert v-model="displayAlert" :message="message"></alert>
    </template>
  </v-container>
</template>

<script>
import {Email} from "@/models/Email.js";
import axios from "axios";
import Alert from "@/components/Alert";

export default {
  name: "emailView",
  props: {
    value: Boolean,
    email: Email,
    isDraft: Boolean
  },
  data() {
    return {
      userId: 0,
      message: '',
      displayAlert: false,
      url: 'http://localhost:8085/Api',
      attachIndex:'',
    }

  },
  computed: {
    show: {
      get() {
        return this.value
      },
      set(value) {
        this.$emit('input', value)
      }
    }
  },
  components: {
    Alert
  },
  methods: {
    close(){
      if( this.isDraft ){
        this.saveEmailToDraft();
      }
      this.show = false;
    },
    saveEmailToDraft() {
      this.email.attachments = null;
      this.sendEmailToServer(this.email, 'Draft');
      this.message = 'Email is saved to draft';
      this.displayAlert = true;
      this.show = false;
    },
    sendEmail() {

      let i = 0;
      let email;
      console.log(this.email.receiver);
      for (i = 0; i < this.email.receiver.length; i++) {
        if (!this.validateEmail(this.email.receiver[i])) {
          break;
        }
      }
      if (i === this.email.receiver.length && this.email.receiver.length !== 0) {
        console.log(this.email.id);
        email = new Email(this.email.id, this.email.subject, this.email.sender, this.email.receiver, this.email.emailBody, this.email.importance, this.email.attachments, null);
        this.sendEmailToServer(email, 'Sent');
        this.show = false;
      } else {
        this.message = 'Email(s) of receivers  not valid';
        this.displayAlert = true;
      }

    },
    validateEmail(email) {
      const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    }

    ,
    sendEmailToServer(email, folderName) {
      let url = this.url + "/sendEmail"
      let data = {
        id: this.userId,
        folderName: folderName,
        email: email
      };
      console.log(data);
      axios.post(url, data)
          .then(function (response) {
            console.log(response.data);
            if (folderName === 'Sent') {
              if (response.data.length === 0) {
                this.message = "Email is sent successfully!";
              } else {
                let emails = response.data;
                this.message = '';
                for (let i = 0; i < emails.length; i++) {
                  this.message = this.message + emails[i] + "\n";
                }
                this.receivers = '';
              }
              this.displayAlert = true;
            }


          }.bind(this));


    },
    Download(path) {
      console.log(path);
      axios.post("http://localhost:8085/Api/Download", JSON.stringify(path), {
        headers: {
          "Content-Type": "application/json",
        },
      });
      alert("Attachment in Downloads!");
    }
  },

  created() {
    this.userId = this.$route.params.id;
  }
}
</script>

